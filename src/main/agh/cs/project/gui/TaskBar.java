package agh.cs.project.gui;

import agh.cs.project.simulation.SimulationEngine;
import agh.cs.project.simulation.SimulationsManager;
import agh.cs.project.utility.AppStyle;
import agh.cs.project.utility.Logger;
import agh.cs.project.utility.Vector2;
import processing.core.PApplet;

public class TaskBar extends GUI {
    private int offset;
    public TaskBar(PApplet context, SimulationsManager simulationsManager) {
        super(new Vector2(AppStyle.WINDOW_SIZE.x, AppStyle.UPPER_BAR_HEIGHT));

        offset = 0;

        Widget addButton = addWidget( new Button(context, "+", (Button b1)-> {
            SimulationEngine simulation = simulationsManager.createSimulation();
            simulationsManager.focusSimulation(simulation); //Set new created simulation as focused


            Widget newButton = addWidget(new Button(context, "Simulation", (Button b2)->{
                simulationsManager.focusSimulation(simulation);
            }));

            newButton.setPosition(new Vector2(offset, 5));
            offset += 5 + newButton.getSize().x;

        }) );
        addButton.setPosition(new Vector2(5, 5));

        offset += 10 + addButton.getSize().x;
    }
}
