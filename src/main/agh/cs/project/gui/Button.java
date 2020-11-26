package agh.cs.project.gui;

import agh.cs.project.utility.AppStyle;
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

    }

    @Override
    protected void onClick() {
        callback.run();
    }

    @Override
    protected void drawPawn(PApplet context) {

        context.textSize(AppStyle.FONT_SIZE);
        setSize(new Vector2((int)(context.textWidth(text)) + 2*AppStyle.BUTTON_MARGIN, AppStyle.FONT_SIZE+AppStyle.BUTTON_MARGIN));

        context.fill(AppStyle.SECONDARY_COLOR.getRGB());
        context.rect(0, 0, getSize().x, getSize().y, 7);


        context.fill(AppStyle.TEXT_COLOR.getRGB());
        context.text(text, AppStyle.BUTTON_MARGIN, getSize().y-10);
        context.noFill();
    }
}
