package agh.cs.project.window;

import agh.cs.project.assetsManager.AssetsManager;
import agh.cs.project.gui.GUI;
import agh.cs.project.gui.SimulationController;
import agh.cs.project.gui.TaskBar;
import agh.cs.project.simulation.SimulationsManager;
import agh.cs.project.utility.AppStyle;
import agh.cs.project.utility.Logger;
import agh.cs.project.utility.Vector2;
import processing.core.PApplet;
import processing.data.JSONObject;
import processing.event.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;

public class Window extends PApplet {
    private ArrayList<GUI> gui;
    private SimulationsManager simulationsManager;


    private void loadAssets() {
        try {
            AssetsManager.ASSETS.put(AppStyle.ANIMAL_ASSET_KEY, loadImage("assets/animal.png"));
            AssetsManager.ASSETS.put(AppStyle.GRASS_ASSET_KEY, loadImage("assets/grass.png"));
            AssetsManager.ASSETS.put(AppStyle.JUNGLE_ASSET_KEY, loadImage("assets/jungle.png"));
            AssetsManager.ASSETS.put(AppStyle.SAVANNA_ASSET_KEY, loadImage("assets/savanna.png"));
        }
        catch(Exception e) {
            Logger.log("Cannot load graphic assets");
        }
        try {
            AssetsManager.ASSETS.put(AppStyle.PARAMETERS_ASSET_KEY, loadJSONObject("assets/parameters.json"));
        }
        catch(Exception e) {
            Logger.log("Cannot load parameters.json");
        }
    }


    @Override
    public void setup() {
        loadAssets();

        getSurface().setTitle("Simulation");

        noFill();
        noStroke();
        textSize(AppStyle.BUTTON_FONT_SIZE);


        simulationsManager = new SimulationsManager();

        gui = new ArrayList<>();
        gui.add(new TaskBar(this, simulationsManager));
        gui.add(new SimulationController(this, simulationsManager));

    }

    @Override
    public void settings() {
        size(AppStyle.WINDOW_SIZE.x, AppStyle.WINDOW_SIZE.y);
    }

    @Override
    public void draw() {
        background(130, 200,130);
        //simulationsManager.setPosition(new Vector2(millis()/10, 0));
        simulationsManager.draw(this);
        for(GUI gui : gui)
            gui.draw(this);
    }


    @Override
    public void mouseDragged() {
        simulationsManager.move(new Vector2( mouseX-pmouseX, mouseY-pmouseY ));
    }

    @Override
    public void mouseWheel(MouseEvent event) {
        simulationsManager.scale(1.0f - ((float)(event.getCount()))/10.0f);
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        if(event.getButton()==LEFT) {
            boolean guiClicked = false;
            Vector2 mousePosition = new Vector2(event.getX(), event.getY());
            for(GUI gui : gui) {
                if (gui.mouseClicked(mousePosition))
                    guiClicked = true;
            }
            if(!guiClicked) {
                simulationsManager.mouseClicked(
                        mousePosition
                                .sub(simulationsManager.getPosition())
                                .multiply(1.f/simulationsManager.getScale())
                );
            }

        }
    }
}
