package agh.cs.project.window;

import agh.cs.project.gui.Button;
import agh.cs.project.gui.GUI;
import agh.cs.project.gui.Widget;
import agh.cs.project.utility.Vector2;
import processing.core.PApplet;
import processing.event.MouseEvent;

public class Window extends PApplet {
    private GUI gui;

    @Override
    public void setup() {
        gui = new GUI(new Vector2(1280, 40));

        Widget a = new Button(
                "+",
                () -> {
                    System.out.println("Hello");
                }
        );
        a.setPosition(new Vector2(5, 5));

        gui.addWidget(a);

        a = new Button(
                "Simulation 1",
                () -> {
                    System.out.println("Hello");
                }
        );
        a.setPosition(new Vector2(42, 5));

        gui.addWidget(a);


        getSurface().setTitle("Simulation");
        noFill();
        noStroke();
    }

    @Override
    public void settings() {
        size(1280, 720);
    }

    @Override
    public void draw() {
        background(130, 200,130);
        gui.draw(this);
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        if(event.getButton()==LEFT)
            gui.mouseClicked(new Vector2(event.getX(), event.getY()));
    }
}
