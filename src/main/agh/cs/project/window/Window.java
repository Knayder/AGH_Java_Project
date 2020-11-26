package agh.cs.project.window;

import agh.cs.project.gui.Button;
import agh.cs.project.gui.GUI;
import agh.cs.project.gui.TaskBar;
import agh.cs.project.gui.Widget;
import agh.cs.project.simulation.SimulationsManager;
import agh.cs.project.utility.AppStyle;
import agh.cs.project.utility.Vector2;
import processing.core.PApplet;
import processing.event.MouseEvent;

import java.util.ArrayList;

public class Window extends PApplet {
    private ArrayList<GUI> gui;
    private SimulationsManager simulationsManager;


    @Override
    public void setup() {
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
