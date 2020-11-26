package agh.cs.project.utility;

public class Area {
    public Area(Vector2 position, Vector2 size) {
        this.position = position;
        this.size = size;
    }

    public boolean contains(Vector2 point) {
        return point.biggerThan(position) && point.smallerThan(position.add(size));
    }

    public Vector2 position;
    public Vector2 size;
}
