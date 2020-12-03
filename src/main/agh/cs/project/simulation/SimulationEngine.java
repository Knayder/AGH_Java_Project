package agh.cs.project.simulation;

import agh.cs.project.assetsManager.AssetsManager;
import agh.cs.project.render.Pawn;
import processing.core.PApplet;
import processing.core.PImage;

public class SimulationEngine extends Pawn {
    public SimulationEngine(SimulationConfig config) {
        this.config = config;
        this.lastUpdate = 0;
        this.speed = 0;

        world = new World(config);
    }

    public void update(PApplet context) {
        if(context.millis() - lastUpdate >= speed) {
            lastUpdate = context.millis();
            world.nextDay();

        }
    }

    @Override
    protected void drawPawn(PApplet context) {
        world.draw(context);
    }

    private SimulationConfig config;

    private World world;

    private int lastUpdate;
    private int speed;
}
