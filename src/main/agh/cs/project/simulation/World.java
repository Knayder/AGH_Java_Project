package agh.cs.project.simulation;

import agh.cs.project.assetsManager.AssetsManager;
import agh.cs.project.render.Pawn;
import agh.cs.project.render.Sprite;
import agh.cs.project.utility.AppStyle;
import agh.cs.project.utility.Vector2;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Random;


public class World extends Pawn {
    private Random random;

    private SimulationConfig config;

    private ArrayList<Statistic> statistics;

    private Sprite savannaTile;
    private Sprite jungleTile;
    private Sprite map;

    private AnimalsManager animalsManager;
    private GrassManager grassManager;




    public World(SimulationConfig config) {
        super();
        random = new Random();

        this.config = config;

        statistics = new ArrayList<>();

        map = null;
        savannaTile = new Sprite((PImage)AssetsManager.ASSETS.get(AppStyle.SAVANNA_ASSET_KEY));
        jungleTile = new Sprite((PImage)AssetsManager.ASSETS.get(AppStyle.JUNGLE_ASSET_KEY));

        animalsManager = new AnimalsManager(config);
        grassManager = new GrassManager(config);

    }

    public void nextDay() {
        grassManager.addNewGrass();
        animalsManager.moveAnimals();
        grassManager.feed(animalsManager);
        animalsManager.reproduce(statistics.size(), config.startEnergy/2, config.worldArea);
        animalsManager.removeDead();
        statistics.add(new Statistic(
                animalsManager.getAnimalsAmount(),
                grassManager.getGrassAmount(),
                animalsManager.getAverageEnergy(),
                animalsManager.getAverageLifeSpanOfDead(),
                animalsManager.getMostCommonGen()
        ));
    }


    public ArrayList<Statistic> getStatistics() {
        return statistics;
    }

    @Override
    protected void drawPawn(PApplet context) {
        if(map == null) {
            PGraphics texture = context.createGraphics(config.size.x * AppStyle.TILE_PIXEL_SIZE, config.size.y * AppStyle.TILE_PIXEL_SIZE);
            PImage jungleImage = (PImage)AssetsManager.ASSETS.get(AppStyle.JUNGLE_ASSET_KEY);
            PImage savannaImage = (PImage)AssetsManager.ASSETS.get(AppStyle.SAVANNA_ASSET_KEY);
            texture.beginDraw();
            for(int y = 0; y<config.size.y; ++y) {
                for(int x = 0; x<config.size.x; ++x) {
                    Vector2 position = new Vector2(x, y);
                    Vector2 pixelPosition = new Vector2(AppStyle.TILE_PIXEL_SIZE * x, AppStyle.TILE_PIXEL_SIZE * y);
                    if(config.jungleArea.contains(position)) {
                        jungleTile.setPosition(pixelPosition);
                        texture.image(jungleImage, pixelPosition.x, pixelPosition.y);
                    }
                    else {
                        savannaTile.setPosition(pixelPosition);
                        texture.image(savannaImage, pixelPosition.x, pixelPosition.y);
                    }
                }
            }
            texture.endDraw();
            map = new Sprite(texture);
        }
        map.draw(context);
        grassManager.draw(context);
        animalsManager.draw(context);

    }
}
