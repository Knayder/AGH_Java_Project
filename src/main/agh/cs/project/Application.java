package agh.cs.project;


import agh.cs.project.utility.Vector2;

public class Application  {
    public Application() {
        simulationWindow = new SimulationWindow(new Vector2(1280, 720), "Simulation");
    }

    public void run() {
    }

    private SimulationWindow simulationWindow;
}
