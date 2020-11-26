package agh.cs.project;


import agh.cs.project.utility.Area;
import agh.cs.project.utility.Vector2;
import processing.core.PApplet;
import uibooster.UiBooster;
import uibooster.components.Form;
import uibooster.model.FilledForm;
import uibooster.model.UiBoosterOptions;

import java.util.ArrayList;

public class Menu {
    public Menu(SimulationWindow simulationWindow) {
        this.simulationWindow = simulationWindow;

        form = new UiBooster().createForm("Simulation Control Center")
                .addText("Simulation Name")
                .addText("Width")
                .addText("Height")
                .addText("Jungle Ratio")
                .addText("Start Energy")
                .addText()
                .addButton("Add simulation", this::createSimulation);

    }

    private void createSimulation() {
        simulationWindow.createSimulation(
                new SimulationConfig()
                        .setSize(new Vector2(
                                filledForm.getByLabel("Width").asInt(),
                                filledForm.getByLabel("Height").asInt()
                        ))
                        .setJungleSize(new Vector2(
                                filledForm.getByLabel("Jungle Width").asInt(),
                                filledForm.getByLabel("Jungle Height").asInt()
                        ))
                        .setName(filledForm.getByLabel("Simulation Name").asString())
        );
    }

    public void open() {
        filledForm = form.run();
        SimulationConfig defaultConfig = new SimulationConfig();
        filledForm.getByLabel("Width").setValue(defaultConfig.size.x);
        filledForm.getByLabel("Height").setValue(defaultConfig.size.y);
        filledForm.getByLabel("Jungle Width").setValue(defaultConfig.jungleSize.x);
        filledForm.getByLabel("Jungle Height").setValue(defaultConfig.jungleSize.y);
        filledForm.getByLabel("Simulation Name").setValue(defaultConfig.name);

    }

    private SimulationWindow simulationWindow;

    private FilledForm filledForm;
    private Form form;
}
