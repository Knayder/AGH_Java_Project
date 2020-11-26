package agh.cs.project;

import agh.cs.project.simulation.SimulationEngine;
import agh.cs.project.utility.Area;
import agh.cs.project.utility.Vector2;
import processing.core.PApplet;
import processing.event.MouseEvent;

public class SimulationWindow extends PApplet {
    public SimulationWindow(Vector2 windowSize, String windowName) {
        this.windowName = windowName;
        this.windowSize = windowSize;
        this.menuButton = new Area(
                new Vector2(0, windowSize.y - 60),
                new Vector2(60, 60)
        );
        this.leftMenuSimulationMargin = 200;

        this.simulation = null;

        this.menu = new Menu(this);

        PApplet.runSketch(new String[]{"Simulation"}, this);
    }

    public void createSimulation(SimulationConfig config) {
        config.setDrawArea(new Area(
                new Vector2(leftMenuSimulationMargin, 0),
                new Vector2(windowSize.x - leftMenuSimulationMargin, windowSize.y)
        ));
        simulation = new SimulationEngine(this, config);
    }


    @Override
    public void settings() {
        size(windowSize.x, windowSize.y);
    }

    @Override
    public void setup() {
        surface.setTitle(windowName);
    }

    @Override
    public void draw() {
        background(255,255,255);
        noStroke();
        fill(37,37,48);
        if(simulation != null)
            simulation.draw();
        rect(menuButton.position.x, menuButton.position.y, menuButton.size.x, menuButton.size.y);
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        if (menuButton.contains(new Vector2(event.getX(), event.getY())))
            menu.open();
    }

    public String getWindowName() {
        return windowName;
    }

    private SimulationEngine simulation;

    private String windowName;
    private Vector2 windowSize;
    private Area menuButton;
    private int leftMenuSimulationMargin;

    private Menu menu;
}
