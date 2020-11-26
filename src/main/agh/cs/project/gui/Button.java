package agh.cs.project.gui;

import agh.cs.project.render.Pawn;
import agh.cs.project.utility.Vector2;
import processing.core.PApplet;

public class Button extends Widget {
    private Runnable callback;
    private String text;

    private int margin;
    private int fontSize;

    public Button(String text, Runnable callback) {
        super(new Vector2(32, 32));
        this.text = text;
        this.callback = callback;

        this.margin = 10;
        this.fontSize = 20;
    }

    @Override
    protected void onClick() {
        callback.run();
    }

    @Override
    protected void drawPawn(PApplet context) {
        context.textSize(fontSize);
        setSize(new Vector2((int)(context.textWidth(text)) + 2*margin, fontSize+margin));

        context.fill(37,37,48);
        context.rect(0, 0, getSize().x, getSize().y, 7);


        context.fill(255,255,255);
        context.text(text, margin, getSize().y-10);
        context.noFill();
    }
}
