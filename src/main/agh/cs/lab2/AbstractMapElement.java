package agh.cs.lab2;

import agh.cs.lab2.utility.Vector2d;

public abstract class AbstractMapElement {
    public AbstractMapElement(Vector2d position) {
        this.position = position;
    }

    protected abstract String stringRepresentation();

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return stringRepresentation();
    }

    protected Vector2d position;
}
