package me.steven.pham.algorithms;

import com.sun.javafx.geom.Vec2d;
import me.steven.pham.decorators.containers.InsertionListeningDequeDecorator;
import me.steven.pham.decorators.containers.InsertionListeningQueueDecorator;
import me.steven.pham.decorators.containers.InsertionListeningSetDecorator;

import java.util.*;
import java.util.function.Consumer;

public class BreadthFirstSearch {

    private Consumer<Vec2d> needVisitingInsertionConsumer = e -> {};
    private Consumer<Vec2d> visitedInsertionConsumer = e -> {};
    private Consumer<Vec2d> pathInsertionConsumer = e -> {};
    private Consumer<Vec2d> currentChangeConsumer = e -> {};

    public void setNeedVisitingInsertionConsumer(Consumer<Vec2d> needVisitingInsertionConsumer) {
        this.needVisitingInsertionConsumer = needVisitingInsertionConsumer;
    }

    public void setVisitedInsertionConsumer(Consumer<Vec2d> visitedInsertionConsumer) {
        this.visitedInsertionConsumer = visitedInsertionConsumer;
    }

    public void setPathInsertionConsumer(Consumer<Vec2d> pathInsertionConsumer) {
        this.pathInsertionConsumer = pathInsertionConsumer;
    }

    public void setCurrentChangeConsumer(Consumer<Vec2d> currentChangeConsumer) {
        this.currentChangeConsumer = currentChangeConsumer;
    }

    public Optional<Deque<Vec2d>> run(Grid grid, Vec2d start, Vec2d target) {

        Queue<Vec2d> needVisiting = new InsertionListeningQueueDecorator<>(new ArrayDeque<>(), needVisitingInsertionConsumer);
        Set<Vec2d> visited = new InsertionListeningSetDecorator<>(new HashSet<>(), visitedInsertionConsumer);
        Vec2d current = new Vec2d(start.x, start.y);
        currentChangeConsumer.accept(current);
        Map<Vec2d, Vec2d> cameFrom = new HashMap<>();
        cameFrom.put(start, start);

        while (true) {
            if (current.equals(target)) {
                Deque<Vec2d> path = new InsertionListeningDequeDecorator<>(new ArrayDeque<>(), pathInsertionConsumer);
                while (current != start) {
                    if (current != target) {
                        path.addLast(current);
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
            currentChangeConsumer.accept(current);
        }
    }

}
