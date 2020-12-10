package agh.cs.project.simulation;

import agh.cs.project.render.Pawn;
import agh.cs.project.utility.Logger;
import agh.cs.project.utility.Vector2;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

import processing.data.JSONObject;

public class SimulationsManager extends Pawn {
    private ArrayList<SimulationEngine> simulations;
    private SimulationEngine current;


    public SimulationsManager() {
        simulations = new ArrayList<>();
        current = null;
    }

    public void slowDown() {
        if(current != null)
            current.slowDown();
    }

    public void speedUp() {
        if(current != null)
            current.speedUp();

    }

    public void pause() {
        if(current != null)
            current.pause();

    }

    public void resume() {
        if(current != null)
            current.resume();
    }

    public void step() {
        if(current != null)
            current.step();
    }

    public ArrayList<Statistic> getStatistics() {
        if(current != null)
            return current.getStatistics();
        return null;
    }

    public int getTilesAmount() {
        if(current != null)
            return current.getTilesAmount();
        return 0;
    }


    public SimulationEngine createSimulation() {
        SimulationEngine simulation = new SimulationEngine(loadConfigFromFile("parameters.json"));

        simulations.add(simulation);
        return simulation;
    }

    private static SimulationConfig loadConfigFromFile(String path) {
        /*
        PApplet temp = new PApplet();
        JSONObject json = temp.loadJSONObject(path);

        return new SimulationConfig()
                .setSize(new Vector2( json.getInt("width"), json.getInt("height") ))
                .setJungleRatio(json.getFloat("jungleRatio"));

         */
        return new SimulationConfig();
    }

    public void focusSimulation(SimulationEngine simulation) {
        if(simulations.contains(simulation)) {
            current = simulation;
        }
    }



    @Override
    protected void drawPawn(PApplet context) {
        for(SimulationEngine simulation : simulations)
            simulation.update(context);
        if(current != null)
            current.draw(context);
    }
}
