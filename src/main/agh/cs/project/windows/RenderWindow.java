package agh.cs.project.windows;

import processing.core.PApplet;

public abstract class RenderWindow extends PApplet {
    public RenderWindow(int width, int height, String windowName) {
        this.windowName = windowName;
        this.width = width;
        this.height = height;
    }
    @Override
    public void settings() {
        size(width, height);
    }

    @Override
    public void setup() {
        surface.setTitle(windowName);
    }

    @Override
    public void draw() {
        background(255,255,255);
    }

    public String getWindowName() {
        return windowName;
    }

    private String windowName;
    private int width;
    private int height;
}
