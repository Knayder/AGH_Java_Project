package agh.cs.project.simulation;

import agh.cs.project.assetsManager.AssetsManager;
import agh.cs.project.render.Pawn;
import agh.cs.project.render.Sprite;
import agh.cs.project.utility.AppStyle;
import agh.cs.project.utility.Vector2;
import processing.core.PApplet;
import processing.core.PImage;


public class World extends Pawn {
    private Vector2 size;
    private Vector2 jungleSize;
    private Vector2 junglePosition;

    private Sprite savannaTile;
    private Sprite jungleTile;

    public World(SimulationConfig config) {
        super();
        size = config.size;
        jungleSize = size.multiply(config.jungleRatio);
        junglePosition = new Vector2( (size.x-jungleSize.x)/2, (size.y-jungleSize.y)/2 );

        savannaTile = new Sprite((PImage)AssetsManager.ASSETS.get(AppStyle.SAVANNA_ASSET_KEY));
        jungleTile = new Sprite((PImage)AssetsManager.ASSETS.get(AppStyle.JUNGLE_ASSET_KEY));
    }

    public void nextDay() {

    }

    @Override
    protected void drawPawn(PApplet context) {
        Animal animal1 = new Animal();
        Animal animal2 = new Animal();
        Animal animal3 = new Animal();

        animal1.setPosition(new Vector2(150, 250));
        animal2.setPosition(new Vector2(200, 250));
        animal3.setPosition(new Vector2(250, 250));



        for(int y = 0; y<size.y; ++y) {
            for(int x = 0; x<size.x; ++x) {
                Vector2 position = new Vector2(x, y);
                Vector2 pixelPosition = new Vector2(AppStyle.TILE_PIXEL_SIZE * x, AppStyle.TILE_PIXEL_SIZE * y);
                if(position.biggerThan(junglePosition) && position.smallerThan(junglePosition.add(jungleSize))) {
                    jungleTile.setPosition(pixelPosition);
                    jungleTile.draw(context);
                }
                else {
                    savannaTile.setPosition(pixelPosition);
                    savannaTile.draw(context);
                }
            }
        }
        animal1.draw(context);
        animal2.draw(context);
        animal3.draw(context);

    }
}
