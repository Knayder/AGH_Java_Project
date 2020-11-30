package agh.cs.project.window;

import agh.cs.project.assetsManager.AssetsManager;
import agh.cs.project.gui.Button;
import agh.cs.project.gui.GUI;
import agh.cs.project.gui.TaskBar;
import agh.cs.project.gui.Widget;
import agh.cs.project.simulation.SimulationsManager;
import agh.cs.project.utility.AppStyle;
import agh.cs.project.utility.Logger;
import agh.cs.project.utility.Vector2;
import processing.core.PApplet;
import processing.core.PImage;
import processing.event.MouseEvent;

import java.util.ArrayList;

public class Window extends PApplet {
    private ArrayList<GUI> gui;
    private SimulationsManager simulationsManager;

    private void loadAssets() {
        AssetsManager.ASSETS.put(AppStyle.ANIMAL_ASSET_KEY, loadImage("assets/animal.png"));
        AssetsManager.ASSETS.put(AppStyle.GRASS_ASSET_KEY, loadImage("assets/grass.png"));
        AssetsManager.ASSETS.put(AppStyle.JUNGLE_ASSET_KEY, loadImage("assets/jungle.png"));
        AssetsManager.ASSETS.put(AppStyle.SAVANNA_ASSET_KEY, loadImage("assets/savanna.png"));
    }


    @Override
    public void setup() {
        loadAssets();

        getSurface().setTitle("Simulation");

        noFill();
        noStroke();
        textSize(AppStyle.FONT_SIZE);

        simulationsManager = new SimulationsManager();

        gui = new ArrayList<>();
        gui.add(new TaskBar(this, simulationsManager));

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
    public void mouseClicked(MouseEvent event) {
        if(event.getButton()==LEFT) {
            for(GUI gui : gui)
                gui.mouseClicked(new Vector2(event.getX(), event.getY()));
        }
    }
}
