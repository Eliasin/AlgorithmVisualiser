    package me.steven.pham.algorithms;

    import com.sun.javafx.geom.Vec2d;
    import java.util.HashSet;
    import java.util.Set;
    import java.util.Vector;

    public class Grid {

    private Vec2d dimensions = new Vec2d(0, 0);
    private Set<Vec2d> neighbourTransformations = new HashSet<>();
    private Set<Vec2d> obstructions = new HashSet<>();

    private Grid() {
       neighbourTransformations.add(new Vec2d(1, 0));
       neighbourTransformations.add(new Vec2d(-1, 0));
       neighbourTransformations.add(new Vec2d(0, 1));
       neighbourTransformations.add(new Vec2d(0, -1));
    }

    public Grid(Vec2d dimensions) {
       this();
       this.dimensions = dimensions;
    }

    public Vec2d getDimensions() {
       return dimensions;
    }

    public void addObstruction(Vec2d node) {
       obstructions.add(node);
    }

    public void addObstruction(Set<Vec2d> nodes) {
       obstructions.addAll(nodes);
    }

    Vector<Vec2d> getNeighbours(final Vec2d node) {
       Vector<Vec2d> neighbours = new Vector<>();
       for (Vec2d transformation : neighbourTransformations) {
           final Vec2d neighbourCandidate = new Vec2d(node.x + transformation.x, node.y + transformation.y);
           if (nodeExists(neighbourCandidate)) {
               neighbours.add(neighbourCandidate);
           }
       }
       return neighbours;
    }

    public Set<Vec2d> getObstructedNodes() {
        return obstructions;
    }

    private boolean nodeObstructed(Vec2d node) {
        return obstructions.contains(node);
    }

    private boolean nodeExists(final Vec2d node) {
       return node.x > 0 && node.x <= dimensions.x && node.y > 0 && node.y <= dimensions.y && !nodeObstructed(node);
    }

    }
