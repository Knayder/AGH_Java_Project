package agh.cs.project.simulation;

import agh.cs.project.render.Pawn;
import processing.core.PApplet;

public class SimulationEngine extends Pawn {
    public SimulationEngine(SimulationConfig config) {
        this.context = context;
        this.config = config;
    }

    @Override
    protected void drawPawn(PApplet context) {

    }

    private PApplet context;
    private SimulationConfig config;
}
