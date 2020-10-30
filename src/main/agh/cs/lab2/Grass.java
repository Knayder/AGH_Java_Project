package agh.cs.lab2;

import agh.cs.lab2.utility.Vector2d;

public class Grass extends AbstractMapElement {
    public Grass(Vector2d position) {
        super(position);
    }

    @Override
    protected String stringRepresentation() {
        return "*";
    }

}
