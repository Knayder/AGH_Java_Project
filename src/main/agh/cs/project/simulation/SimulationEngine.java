package agh.cs.project.simulation;

import agh.cs.project.assetsManager.AssetsManager;
import agh.cs.project.render.Pawn;
import processing.core.PApplet;
import processing.core.PImage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SimulationEngine extends Pawn {
    public SimulationEngine(SimulationConfig config) {
        this.config = config;
        this.lastUpdate = 0;
        this.speed = 0;
        paused = false;

        world = new World(config);


    }

    public void slowDown() {
        if(this.speed < 60000)
            this.speed += 50;
    }

    public void speedUp() {
        if(this.speed >= 50)
            this.speed -= 50;

    }

    public void pause() {
        paused = true;
    }

    public void resume() {
        paused = false;
    }

    public void step() {
        world.nextDay();

    }

    public ArrayList<Statistic> getStatistics() {
        return world.getStatistics();
    }

    public int getTilesAmount() {
        return config.size.x * config.size.y;
    }


    public void update(PApplet context) {
        if(!paused && context.millis() - lastUpdate >= speed) {
            lastUpdate = context.millis();
            step();
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
    private boolean paused;
}
