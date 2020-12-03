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

    private Vector2 size;
    private Area jungleArea;

    private Sprite savannaTile;
    private Sprite jungleTile;

    private AnimalsManager animalsManager;
    private ArrayList<Grass> grasses;

    public World(SimulationConfig config) {
        super();
        random = new Random();

        this.config = config;

        size = config.size;

        Vector2 jungleSize = size.multiply(config.jungleRatio);
        Vector2 junglePosition = new Vector2( (size.x-jungleSize.x)/2, (size.y-jungleSize.y)/2 );
        jungleArea = new Area(junglePosition, jungleSize);

        savannaTile = new Sprite((PImage)AssetsManager.ASSETS.get(AppStyle.SAVANNA_ASSET_KEY));
        jungleTile = new Sprite((PImage)AssetsManager.ASSETS.get(AppStyle.JUNGLE_ASSET_KEY));

        animalsManager = new AnimalsManager(config);
        grasses = new ArrayList<>();
    }

    public void nextDay() {
        /*
        [X] adding new grass
        [X] move animals
        [X] eating
        [X] reproduction
        [X] removing dead animals
         */

        addNewGrass();
        animalsManager.moveAnimals();
        feed();
        animalsManager.reproduce(config.startEnergy/2, new Area(new Vector2(0,0), size));
        animalsManager.removeDead();
    }

    private void addNewGrass() {

        Grass grass = new Grass();

        Vector2 position;
        do {
            position = new Vector2( random.nextInt(config.size.x), random.nextInt(config.size.y));
        } while(jungleArea.contains(position));
        grass.setWorldPosition(position);

        grasses.add(grass);

        grass = new Grass();
        do {
            position = new Vector2(
                    jungleArea.position.x + random.nextInt(jungleArea.size.x),
                    jungleArea.position.y + random.nextInt(jungleArea.size.y)
            );
        } while(!jungleArea.contains(position));
        grass.setWorldPosition(position);

        grasses.add(grass);
        throw new IllegalArgumentException("MAKE GRASS NOT BEING ABLE TO SPAWN AT THE SAME TILE!");
    }

    private void feed() {
        ArrayList<Grass> newGrasses = new ArrayList<>();
        for(Grass grass : grasses) {
            if (!animalsManager.hasBeenEaten(grass.getWorldPosition(), config.plantEnergy))
                newGrasses.add(grass);
        }
        grasses = newGrasses;
    }

    @Override
    protected void drawPawn(PApplet context) {
        for(int y = 0; y<size.y; ++y) {
            for(int x = 0; x<size.x; ++x) {
                Vector2 position = new Vector2(x, y);
                Vector2 pixelPosition = new Vector2(AppStyle.TILE_PIXEL_SIZE * x, AppStyle.TILE_PIXEL_SIZE * y);
                if(jungleArea.contains(position)) {
                    jungleTile.setPosition(pixelPosition);
                    jungleTile.draw(context);
                }
                else {
                    savannaTile.setPosition(pixelPosition);
                    savannaTile.draw(context);
                }
            }
        }
        for(Grass grass : grasses)
            grass.draw(context);
        animalsManager.draw(context);

    }
}
