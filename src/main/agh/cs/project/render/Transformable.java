package agh.cs.project.render;

import agh.cs.project.utility.Vector2;
import processing.core.PApplet;

public class Transformable {
    private Vector2 position;
    private float scale;

    public Transformable() {
        position = new Vector2(0, 0);
        scale = 1.0f;
    }

    protected void applyTransformation(PApplet context) {
        context.translate(position.x, position.y);
        context.scale(scale);
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
}
