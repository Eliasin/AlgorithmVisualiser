package me.steven.pham.algorithms;

import com.sun.javafx.geom.Vec2d;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.Vector;
import java.util.function.Function;

public class BreadthFirstSearch {

    private Runnable onStepStart = () -> {};
    private Runnable onStepEnd = () -> {};
    private Runnable onAlgorithmEnd = () -> {};

    private LinkedList<Vec2d> result = null;

    private Queue<Vec2d> discovered = new LinkedList<>();
    private Vector<Vec2d> visited = new Vector<>();
    private Vec2d current = new Vec2d(0, 0);
    private Vec2d target = new Vec2d(0, 0);
    private Grid grid = new Grid();

    public BreadthFirstSearch(Grid grid, Vec2d current, Vec2d target) {
        this.grid = grid;
        this.current = current;
        this.target = target;
    }

    public void resetState() {
        discovered = new LinkedList<>();
        visited = new Vector<>();
        result = null;
        grid = null;
        current = new Vec2d(0, 0);
        target = new Vec2d(0, 0);
    }

    public Optional<LinkedList<Vec2d>> getResult() {
        return result == null ? Optional.empty() : Optional.of(result);
    }

    public void setOnStepStartRunnable(Runnable runnable) {
        onStepStart = runnable;
    }

    public void setOnStepEndRunnable(Runnable runnable) {
        onStepEnd = runnable;
    }

    public void setOnAlgorithmEndRunnable(Runnable runnable) {
        onAlgorithmEnd = runnable;
    }

    public void step() {
        onStepStart.run();
        if (grid == null || current == target) {
            onAlgorithmEnd.run();
            return;
        }

        onStepEnd.run();
    }

}

class AlgorithmStopException extends Exception {

    public AlgorithmStopException(String message) {
        super(message);
    }
}
