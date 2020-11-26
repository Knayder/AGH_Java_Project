package agh.cs.project.gui;

import agh.cs.project.render.Pawn;
import agh.cs.project.gui.Widget;
import agh.cs.project.utility.Vector2;
import processing.core.PApplet;

import java.util.ArrayList;

public class GUI extends Pawn {
    private ArrayList<Widget> widgets;

    public GUI() {
        widgets = new ArrayList<>();
    }

    public void addWidget(Widget widget) {
        widgets.add(widget);
    }

    public void removeWidget(Widget widget) {
        widgets.remove(widget);
    }

    public void mouseClicked(Vector2 mousePosition) {
        for(Widget widget : widgets) {
            widget.mouseClicked(mousePosition);
        }
    }

    @Override
    protected void drawPawn(PApplet context) {
        for(Widget widget : widgets) {
            widget.draw(context);
        }
    }
}
