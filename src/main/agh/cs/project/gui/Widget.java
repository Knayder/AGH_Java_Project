package agh.cs.project.gui;

import agh.cs.project.render.Pawn;
import agh.cs.project.utility.Vector2;
import processing.core.PApplet;
import processing.event.MouseEvent;

public abstract class Widget extends Pawn {
    private Vector2 size;

    public Widget(Vector2 size) {
        super();
        this.size = size;
    }

    protected void onClick() {

    }

    public Vector2 getSize() {
        return size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }

    final public void mouseClicked(Vector2 mousePosition) {
        if (
                mousePosition.biggerThan(getPosition()) &&
                mousePosition.smallerThan(getPosition().add(size))
        ) {
            onClick();
        }
    }
}
