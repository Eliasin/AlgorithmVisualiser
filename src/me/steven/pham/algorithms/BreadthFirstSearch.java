package me.steven.pham.algorithms;

import com.sun.javafx.geom.Vec2d;
import me.steven.pham.me.steven.pham.containerDecorators.InsertionListeningDequeDecorator;
import me.steven.pham.me.steven.pham.containerDecorators.InsertionListeningQueueDecorator;
import me.steven.pham.me.steven.pham.containerDecorators.InsertionListeningSetDecorator;

import java.util.*;

public class BreadthFirstSearch {

    private Runnable needVisitingAddRunnable = () -> {};
    private Runnable visitedAddRunnable = () -> {};
    private Runnable pathAddRunnable = () -> {};

    public void setNeedVisitingAddRunnable(Runnable runnable) {
        needVisitingAddRunnable = runnable;
    }

    public void setVisitedAddRunnable(Runnable runnable) {
        visitedAddRunnable = runnable;
    }

    public void setPathAddRunnable(Runnable runnable) {
        pathAddRunnable = runnable;
    }

    private <T>void reverseDeque(Deque<T> deque) {
        for (int i = 0; i < deque.size() / 2; i++) {

        }
    }

    public Optional<Deque<Vec2d>> run(Grid grid, Vec2d start, Vec2d target) {

        Queue<Vec2d> needVisiting = new InsertionListeningQueueDecorator<>(new ArrayDeque<>(), e -> {});
        Set<Vec2d> visited = new InsertionListeningSetDecorator<>(new HashSet<>(), e -> {});
        Vec2d current = new Vec2d(start.x, start.y);
        Map<Vec2d, Vec2d> cameFrom = new HashMap<>();
        cameFrom.put(start, start);

        while (true) {
            if (current.equals(target)) {
                Deque<Vec2d> path = new InsertionListeningDequeDecorator<>(new ArrayDeque<>(), e -> {});
                while (current != start) {
                    if (current != target) {
                        path.add(current);
                    }
                    current = cameFrom.get(current);
                }

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
            current = needVisiting.remove();
        }
    }

}
