package agh.cs.project.simulation;

import agh.cs.project.assetsManager.AssetsManager;
import agh.cs.project.render.Pawn;
import agh.cs.project.render.Sprite;
import agh.cs.project.utility.AppStyle;
import agh.cs.project.utility.Area;
import agh.cs.project.utility.Vector2;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Random;


public class World extends Pawn {
    private Random random;

    private SimulationConfig config;

    private Sprite savannaTile;
    private Sprite jungleTile;

    private AnimalsManager animalsManager;
    private GrassManager grassManager;


    public World(SimulationConfig config) {
        super();
        random = new Random();

        this.config = config;

        savannaTile = new Sprite((PImage)AssetsManager.ASSETS.get(AppStyle.SAVANNA_ASSET_KEY));
        jungleTile = new Sprite((PImage)AssetsManager.ASSETS.get(AppStyle.JUNGLE_ASSET_KEY));

        animalsManager = new AnimalsManager(config);
        grassManager = new GrassManager(config);

    }

    public void nextDay() {
        System.out.println(grassManager.getEmptySpots());
        grassManager.addNewGrass();
        animalsManager.moveAnimals();
        grassManager.feed(animalsManager);
        animalsManager.reproduce(config.startEnergy/2, config.worldArea);
        animalsManager.removeDead();
    }

    @Override
    protected void drawPawn(PApplet context) {
        for(int y = 0; y<config.size.y; ++y) {
            for(int x = 0; x<config.size.x; ++x) {
                Vector2 position = new Vector2(x, y);
                Vector2 pixelPosition = new Vector2(AppStyle.TILE_PIXEL_SIZE * x, AppStyle.TILE_PIXEL_SIZE * y);
                if(config.jungleArea.contains(position)) {
                    jungleTile.setPosition(pixelPosition);
                    jungleTile.draw(context);
                }
                else {
                    savannaTile.setPosition(pixelPosition);
                    savannaTile.draw(context);
                }
            }
        }
        grassManager.draw(context);
        animalsManager.draw(context);

    }
}
