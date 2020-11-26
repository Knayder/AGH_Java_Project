package agh.cs.project.window;

import processing.core.PApplet;
import processing.event.MouseEvent;

public class Window extends PApplet {
    @Override
    public void setup() {
        getSurface().setTitle("Simulation");
        noStroke();
        fill(0);
    }

    @Override
    public void settings() {
        size(1280, 720);
    }

    @Override
    public void draw() {
        background(255);
        fill(0,0,0);
        //rect(0, 0, 100, 100);
        pushMatrix();
        translate(-50, -50);
        rotate(0.1f);
        translate(50, 50);

        //translate();
        fill(255,0,255);
        rect(0, 0, 100, 100);
        popMatrix();
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        if(event.getButton()==LEFT)
            System.out.println(event.getX() + ", " + event.getY());
    }
}
