package me.steven.pham;

import com.sun.javafx.geom.Vec2d;
import me.steven.pham.algorithms.BreadthFirstSearch;
import me.steven.pham.algorithms.Grid;
import me.steven.pham.views.GridView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {

    private Grid grid = new Grid(new Vec2d(15, 15));
    private List<GridBundle> grids = new ArrayList<>();
    private GridView gridView = null;
    private JFrame frame = new JFrame();

    public static void main(String[] args) {
        new Main();
    }

    private void initWindow() {
        frame.setTitle("AlgorithmVisualiser");
        frame.add(gridView);

        int windowWidth = 1000;
        int windowHeight = 1000;
        frame.setBounds(0, 0, windowWidth, windowHeight);
        frame.setPreferredSize(new Dimension(windowWidth, windowHeight));
        frame.setLayout(null);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                System.exit(0);
            }
        });
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();
    }

    private void mainLoop() {
        BreadthFirstSearch bfs = new BreadthFirstSearch();
        gridView.setBFSConsumers(bfs);

        while (true) {
            for (GridBundle gridBundle : grids) {
                gridView.setup(gridBundle.grid.getDimensions(), gridBundle.grid.getObstructedNodes());
                bfs.run(gridBundle.grid, gridBundle.start, gridBundle.target);

                try {
                    Thread.sleep(3000);
                }
                catch (InterruptedException ignore) {}
            }
        }
    }

    private void setupGrid() {
        Grid g1 = new Grid(new Vec2d(10, 10));
        g1.addObstruction(new Vec2d(3,3));
        g1.addObstruction(new Vec2d(3, 4));
        g1.addObstruction(new Vec2d(2, 5));
        g1.addObstruction(new Vec2d(4, 3));
        g1.addObstruction(new Vec2d(5, 3));
        g1.addObstruction(new Vec2d(2, 6));
        g1.addObstruction(new Vec2d(2, 7));
        g1.addObstruction(new Vec2d(2, 8));
        g1.addObstruction(new Vec2d(1, 9));
        g1.addObstruction(new Vec2d(4, 5));
        g1.addObstruction(new Vec2d(5, 6));
        g1.addObstruction(new Vec2d(6, 7));
        g1.addObstruction(new Vec2d(6, 8));
        g1.addObstruction(new Vec2d(8, 3));
        g1.addObstruction(new Vec2d(7, 3));
        g1.addObstruction(new Vec2d(6, 3));
        g1.addObstruction(new Vec2d(9, 4));
        g1.addObstruction(new Vec2d(5, 1));
        g1.addObstruction(new Vec2d(3, 2));
        g1.addObstruction(new Vec2d(7, 5));
        g1.addObstruction(new Vec2d(8, 6));

        grids.add(new GridBundle(g1, new Vec2d(1, 1), new Vec2d(4, 9)));

        Grid g2 = new Grid(new Vec2d(12, 12));
        for (int i = 1; i <= 11; i++) {
            g2.addObstruction(new Vec2d(6, i));
        }
        for (int i = 1; i <= 4; i++) {
            g2.addObstruction(new Vec2d(i, 11));
        }
        for (int i = 2; i <= 11; i++) {
            g2.addObstruction(new Vec2d(4, i));
        }
        g2.addObstruction(new Vec2d(3, 2));
        g2.addObstruction(new Vec2d(2, 2));
        g2.addObstruction(new Vec2d(1, 6));
        g2.addObstruction(new Vec2d(2, 6));
        g2.addObstruction(new Vec2d(1, 5));
        for (int i = 2; i <= 12; i++) {
            g2.addObstruction(new Vec2d(10, i));
        }
        g2.addObstruction(new Vec2d(7, 10));
        g2.addObstruction(new Vec2d(8, 10));

        g2.addObstruction(new Vec2d(9, 8));
        g2.addObstruction(new Vec2d(8, 8));

        g2.addObstruction(new Vec2d(7, 6));
        g2.addObstruction(new Vec2d(8, 6));

        g2.addObstruction(new Vec2d(7, 4));
        g2.addObstruction(new Vec2d(8, 4));

        grids.add(new GridBundle(g2, new Vec2d(1, 7), new Vec2d(11, 11)));

        Grid g3 = new Grid(new Vec2d(7, 7));

        for (int i = 1; i <= 6; i++) {
            g3.addObstruction(new Vec2d(3, i));
        }

        g3.addObstruction(new Vec2d(6, 5));
        g3.addObstruction(new Vec2d(7, 5));
        g3.addObstruction(new Vec2d(6, 4));
        g3.addObstruction(new Vec2d(6, 3));

        grids.add(new GridBundle(g3, new Vec2d(1, 4), new Vec2d(7, 1)));
    }

    public Main() {
        setupGrid();
        gridView = new GridView();
        initWindow();
        mainLoop();
    }

}

class GridBundle {
    public Grid grid;
    Vec2d start;
    Vec2d target;

    public GridBundle(Grid grid, Vec2d start, Vec2d target) {
        this.grid = grid;
        this.start = start;
        this.target = target;
    }
}