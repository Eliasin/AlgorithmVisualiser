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

        int windowWidth = 1800;
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
            }
        }
    }

    private void setupGrid() {
        grids.add(new GridBundle(new Grid(new Vec2d(10, 10)), new Vec2d(1, 1), new Vec2d(4, 9)));
        grids.add(new GridBundle(new Grid(new Vec2d(15, 15)), new Vec2d(1, 5), new Vec2d(11, 11)));
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