package agh.cs.project.simulation;

import agh.cs.project.assetsManager.AssetsManager;
import agh.cs.project.render.Pawn;
import agh.cs.project.render.Sprite;
import agh.cs.project.utility.AppStyle;
import processing.core.PApplet;
import processing.core.PImage;

public class Animal extends Pawn {
    private Sprite sprite;

    public Animal() {
        sprite = new Sprite((PImage)AssetsManager.ASSETS.get(AppStyle.ANIMAL_ASSET_KEY));
        sprite.setCenterOrigin();
    }

    @Override
    protected void drawPawn(PApplet context) {
        sprite.rotate(context.millis()/400.f);
        sprite.draw(context);
    }
}
