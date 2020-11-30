package agh.cs.project.simulation;

import agh.cs.project.render.Pawn;
import agh.cs.project.utility.Logger;
import agh.cs.project.utility.Vector2;
import processing.core.PApplet;

import java.util.ArrayList;

import processing.data.JSONObject;

public class SimulationsManager extends Pawn {
    private ArrayList<SimulationEngine> simulations;
    private SimulationEngine current;


    public SimulationsManager() {
        simulations = new ArrayList<>();
        current = null;
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
