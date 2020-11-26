package agh.cs.project.simulation;

import agh.cs.project.render.Pawn;
import processing.core.PApplet;

public class World extends Pawn {
    public World() {
        super();
    }

    @Override
    protected void drawPawn(PApplet context) {
        context.rect(0, 0, 500, 500);
    }
}
