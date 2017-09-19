package me.steven.pham;

import com.sun.javafx.geom.Vec2d;
import me.steven.pham.algorithms.BreadthFirstSearch;
import me.steven.pham.algorithms.Grid;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Deque;
import java.util.Optional;

public class Main extends Frame {

    public static void main(String[] args) {
        BreadthFirstSearch bfs = new BreadthFirstSearch();
        Grid grid = new Grid(new Vec2d(12, 12));

        Optional<Deque<Vec2d>> path = bfs.run(grid, new Vec2d(1,1 ), new Vec2d(3, 3));

        if (path.isPresent()) {
            for (Vec2d node : path.get()) {
                System.out.println(node.x + " " + node.y);
            }
        }
        else {
            System.out.println("No path present");
        }

        new Main();
    }

    public Main() {
        super("AlgorithmVisualiser");

        setSize(1920, 1080);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });
    }

    public void paint(Graphics g) {
        g.setColor(new Color(0xFF000000, true));
        g.fillRect(100, 100, 200, 200);
    }
}
