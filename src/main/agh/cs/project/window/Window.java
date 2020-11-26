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
        gui = new GUI();

        Widget a = new Button(
                "Testowy przycisk",
                () -> {
                    System.out.println("Hello");
                }
        );
        a.setPosition(new Vector2(40, 40));

        gui.addWidget(a);

        System.out.println(a.getSize());


        getSurface().setTitle("Simulation");
    }

    @Override
    public void settings() {
        size(1280, 720);
    }

    @Override
    public void draw() {
        background(255);
        gui.draw(this);
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        if(event.getButton()==LEFT)
            gui.mouseClicked(new Vector2(event.getX(), event.getY()));
    }
}
