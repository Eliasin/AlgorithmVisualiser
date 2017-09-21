package me.steven.pham;

import com.sun.javafx.geom.Vec2d;
import me.steven.pham.algorithms.BreadthFirstSearch;
import me.steven.pham.algorithms.Grid;
import me.steven.pham.views.GridView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.Timer;

public class Main {

    private Grid grid = new Grid(new Vec2d(15, 15));
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

        Optional<Deque<Vec2d>> path = bfs.run(grid, new Vec2d(1, 1), new Vec2d(15, 13));
        if (path.isPresent()) {
            for (Vec2d node : path.get()) {
                System.out.println(node);
            }
        }

    }

    private void setupGrid() {
        grid.addObstruction(new Vec2d(2, 1));
    }

    public Main() {
        setupGrid();
        gridView = new GridView(grid.getDimensions(), grid.getObstructedNodes());
        initWindow();
        mainLoop();
    }

}
