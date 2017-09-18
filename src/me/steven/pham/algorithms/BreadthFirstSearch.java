package me.steven.pham.algorithms;

import com.sun.javafx.geom.Vec2d;
import me.steven.pham.me.steven.pham.containerDecorators.LinkedListDecorator;

import java.util.*;

public class BreadthFirstSearch {

    private Runnable needVisitingAddRunnable = () -> {};
    private Runnable visitedAddRunnable = () -> {};
    private Runnable pathAddRunnable = () -> {};

    public Optional<LinkedList<Vec2d>> run(Grid grid, Vec2d start, Vec2d target) {

        Queue<Vec2d> needVisiting = new LinkedListDecorator<>();
        LinkedList<Vec2d> visited = new LinkedListDecorator<>();
        Vec2d current = new Vec2d(start.x, start.y);
        Map<Vec2d, Vec2d> cameFrom = new HashMap<>();
        cameFrom.put(start, start);

        while (true) {
            if (current == target) {
                LinkedList<Vec2d> path = new LinkedListDecorator<>();
                while (current != start) {
                    if (current != target) {
                        path.add(current);
                    }
                    current = cameFrom.get(current);
                }
                Collections.reverse(path);
                return Optional.of(path);
            }
            for (Vec2d node : grid.getNeighbours(current)) {
                if (!visited.contains(node)) {
                    needVisiting.add(node);
                    cameFrom.put(node, current);
                }
            }
            if (needVisiting.isEmpty()) {
                return Optional.empty();
            }
            visited.add(current);
        }
    }

}
