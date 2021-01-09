package agh.cs.project.simulation;

import agh.cs.project.assetsManager.AssetsManager;
import agh.cs.project.render.Sprite;
import agh.cs.project.utility.AppStyle;
import processing.core.PApplet;
import processing.core.PImage;

public class Grass extends PawnWorldElement {
    private Sprite sprite;  // pomieszanie logiki aplikacji z wizualizacją
    public Grass() {
        sprite = new Sprite((PImage) AssetsManager.ASSETS.get(AppStyle.GRASS_ASSET_KEY));
    }

    @Override
    protected void drawPawn(PApplet context) {
        sprite.draw(context);
    }
}
