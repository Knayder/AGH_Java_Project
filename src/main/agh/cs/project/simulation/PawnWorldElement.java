package agh.cs.project.simulation;

import agh.cs.project.render.Pawn;
import agh.cs.project.utility.AppStyle;
import agh.cs.project.utility.Vector2;
import processing.core.PApplet;

public abstract class PawnWorldElement extends Pawn {
    private Vector2 worldPosition;
    public void setWorldPosition(Vector2 worldPosition) {
        this.worldPosition = worldPosition;
        setPosition(worldPosition.multiply(AppStyle.TILE_PIXEL_SIZE));
    }

    public void moveWorldPosition(Vector2 offset) {
        worldPosition = worldPosition.add(offset);
        setPosition(worldPosition.multiply(AppStyle.TILE_PIXEL_SIZE));
    }

    public Vector2 getWorldPosition() {
        return worldPosition;
    }
}
