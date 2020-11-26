package agh.cs.project.simulation;

import agh.cs.project.SimulationConfig;
import processing.core.PApplet;

public class SimulationEngine {
    public SimulationEngine(PApplet context, SimulationConfig config) {
        this.context = context;
        this.config = config;
    }

    public void draw() {
        context.rect(50, 50, 200, 200);
    }

    private PApplet context;
    private SimulationConfig config;
}
