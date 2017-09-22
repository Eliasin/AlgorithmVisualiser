package me.steven.pham.views;

import com.sun.istack.internal.NotNull;
import com.sun.javafx.geom.Dimension2D;
import com.sun.javafx.geom.Vec2d;
import me.steven.pham.algorithms.BreadthFirstSearch;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class GridView extends JPanel {

    private static final Field tmpComponentsField;
    private static final Method dirtyPaintingMethod;

    private Vec2d gridDimensions = new Vec2d();
    private List<List<Color>> gridColors = new ArrayList<>();

    private static final int visitedColor = 0x98fb98;
    private static final int needsVisitingColor = 0xadd8e6;
    private static final int pathColor = 0x0fb40f;
    private static final int currentColor = 0xffff00;
    private static final int targetColor = 0xff0000;
    private static final int obstructionColor = 0xa0956f;

    private boolean done = true;

    public GridView() {
        setBounds(0, 0, 1920, 1080);
    }

    public GridView(Vec2d gridDimensions, Set<Vec2d> obstructions) {
        this();
        setup(gridDimensions, obstructions);
    }

    public void setup(Vec2d gridDimensions, Set<Vec2d> obstructions) {
        done = false;
        gridColors.clear();
        this.gridDimensions = new Vec2d(gridDimensions.x, gridDimensions.y);
        for (int i = 0; i < gridDimensions.x; i++) {
            gridColors.add(new ArrayList<>());
            for (int j = 0; j < gridDimensions.y; j++) {
                if (obstructions.contains(new Vec2d(i + 1, j + 1))) {
                    gridColors.get(i).add(new Color(obstructionColor, false));
                }
                else {
                    gridColors.get(i).add(new Color(Color.white.getRGB(), false));
                }
            }
        }
    }

    public void setBFSConsumers(@NotNull BreadthFirstSearch bfs) {
        bfs.setCurrentChangeConsumer(this::currentChangeConsumer);
        bfs.setNeedVisitingInsertionConsumer(this::needsVisitingInsertionConsumer);
        bfs.setVisitedInsertionConsumer(this::visitedInsertionConsumer);
        bfs.setPathInsertionConsumer(this::pathInsertionConsumer);
        bfs.setTargetChangeConsumer(this::targetChangeConsumer);
        bfs.setRepaintListener(this::repaint);
        bfs.setDoneListener(() -> done = true);
    }

    static {
        Field tmpCompField;
        Method paintMtd;
        try {
            tmpCompField = RepaintManager.class.getDeclaredField("tmpDirtyComponents");
            paintMtd = RepaintManager.class.getDeclaredMethod("paintDirtyRegions", Map.class);
            tmpCompField.setAccessible(true);
            paintMtd.setAccessible(true);
        }
        catch (ReflectiveOperationException e) {
            tmpCompField = null;
            paintMtd = null;
        }
        tmpComponentsField = tmpCompField;
        dirtyPaintingMethod = paintMtd;
    }

    public void repaint() {
        RepaintManager thisManager = RepaintManager.currentManager(this);
        thisManager.markCompletelyDirty(this);
        try {
            Map<Component,Rectangle> tmpComponents = new ConcurrentHashMap<>((Map<Component, Rectangle>) tmpComponentsField.get(thisManager));
            dirtyPaintingMethod.invoke(thisManager, tmpComponents);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        if (done) {
            return;
        }

        Dimension2D windowDimensions = new Dimension2D(this.getParent().getWidth(), this.getParent().getHeight());

        g.setColor(Color.white);
        g.fillRect(0, 0, (int) windowDimensions.width, (int) windowDimensions.height);

        int gridLength = (int) Math.min(windowDimensions.width, windowDimensions.height) / (int)gridDimensions.x;
        for (int i = 0; i < (int) Math.min(gridDimensions.x, gridColors.size()); i++) {
            for (int j = 0; j < (int) Math.min(gridDimensions.y, gridColors.get(i).size()); j++) {
                g.setColor(gridColors.get(i).get(j));
                g.fillRect(i * gridLength, j * gridLength, gridLength, gridLength);
                g.setColor(Color.black);
                g.drawRect(i * gridLength, j * gridLength, gridLength, gridLength);
            }
        }

    }

    private void changeAllColorsOfTo(Color of, Color to) {
        for (int i = 0; i < gridColors.size(); i++) {
            for (int j = 0; j < gridColors.get(i).size(); j++) {
                if (gridColors.get(i).get(j).equals(of)) {
                    gridColors.get(i).set(j, new Color(to.getRGB(), false));
                }
            }
        }
    }

    private void currentChangeConsumer(Vec2d current) {
        changeAllColorsOfTo(new Color(currentColor, false), new Color(visitedColor,false));

        gridColors.get((int) current.x - 1).set((int) current.y - 1, new Color(currentColor, false));
    }

    private void needsVisitingInsertionConsumer(Vec2d e) {
        gridColors.get((int)e.x - 1).set((int) e.y - 1, new Color(needsVisitingColor, false));
    }

    private void visitedInsertionConsumer(Vec2d e) {
        gridColors.get((int)e.x - 1).set((int) e.y - 1, new Color(visitedColor, false));
    }

    private void pathInsertionConsumer(Vec2d e) {
        gridColors.get((int)e.x - 1).set((int) e.y - 1, new Color(pathColor, false));
    }

    private void targetChangeConsumer(Vec2d target) {
        changeAllColorsOfTo(new Color(targetColor, false), new Color(visitedColor, false));
        gridColors.get((int) target.x - 1).set((int) target.y - 1, new Color(targetColor, false));
    }

}