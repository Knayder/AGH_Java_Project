package agh.cs.project.gui;

import agh.cs.project.render.Pawn;
import agh.cs.project.utility.AppStyle;
import agh.cs.project.utility.Vector2;
import processing.core.PApplet;

import java.util.ArrayList;

public class GUI extends Pawn {
    private ArrayList<Widget> widgets;
    private Vector2 size;

    public GUI(Vector2 size) {
        widgets = new ArrayList<>();
        this.size = size;
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
        context.fill(AppStyle.MAIN_COLOR.getRGB());
        context.rect(0, 0, size.x, size.y);
        context.noFill();
        for(Widget widget : widgets) {
            widget.draw(context);
        }
    }
}
