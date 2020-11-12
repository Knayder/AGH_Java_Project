package agh.cs.lab2;

import agh.cs.lab2.utility.Vector2d;

public class RectangularMap extends AbstractWorldMap {
    public RectangularMap(int width, int height) {
        super();
        this.lowerLeftCorner = new Vector2d(0,0);
        this.upperRightCorner = new Vector2d(width, height);    // width - 1
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return
            position.follows(lowerLeftCorner) &&
            position.precedes(upperRightCorner) &&
            !isOccupied(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    protected Vector2d[] getBoundaries() {
        return new Vector2d[]{lowerLeftCorner, upperRightCorner};
    }

    private Vector2d lowerLeftCorner;
    private Vector2d upperRightCorner;
}
