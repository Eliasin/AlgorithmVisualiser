package me.steven.pham;

import com.sun.javafx.geom.Vec2d;
import me.steven.pham.algorithms.BreadthFirstSearch;
import me.steven.pham.algorithms.Grid;

import java.util.LinkedList;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        BreadthFirstSearch bfs = new BreadthFirstSearch();
        Grid grid = new Grid(new Vec2d(5, 5));

        Optional<LinkedList<Vec2d>> path = bfs.run(grid, new Vec2d(1, 1), new Vec2d(3, 3));
        if (path.isPresent()) {
            for (Vec2d node : path.get()) {
                System.out.println(node.x + " " + node.y);
            }
        }
        else {
            System.out.println("No path found");
        }
    }
}
