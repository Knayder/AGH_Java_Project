package agh.cs.project.simulation;

import agh.cs.project.render.Pawn;
import agh.cs.project.utility.Area;
import agh.cs.project.utility.Vector2;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class GrassManager extends Pawn {
    private Random random;

    private SimulationConfig config;

    private ArrayList<Grass> grasses;
    private HashSet<Vector2> emptyOuterJungle;
    private HashSet<Vector2> emptyInnerJungle;

    public GrassManager(SimulationConfig config) {
        random = new Random();
        this.config = config;
        grasses = new ArrayList<>();

        emptyOuterJungle = new HashSet<>();
        emptyInnerJungle = new HashSet<>();

        for(int y = 0; y<config.size.y; ++y) {
            for(int x = 0; x<config.size.x; ++x) {
                Vector2 position = new Vector2(x, y);
                if(config.jungleArea.contains(position))
                    emptyInnerJungle.add(position);
                else
                    emptyOuterJungle.add(position);
            }
        }
    }

    private static Vector2 getElementAt(HashSet<Vector2> elements, int index) {
        for(Vector2 position : elements) {
            if(index == 0)
                return position;
            index--;
        }
        return null;
    }

    public void addNewGrass() {
        int innerSize = emptyInnerJungle.size();
        if(innerSize > 0) {
            Vector2 innerPosition = getElementAt(emptyInnerJungle, random.nextInt(innerSize));
            Grass innerGrass = new Grass();
            innerGrass.setWorldPosition(innerPosition);
            grasses.add(innerGrass);
            emptyInnerJungle.remove(innerPosition);
        }

        int outerSize = emptyOuterJungle.size();
        if(outerSize > 0) {
            Vector2 outerPosition = getElementAt(emptyOuterJungle, random.nextInt(outerSize));
            Grass outerGrass = new Grass();
            outerGrass.setWorldPosition(outerPosition);
            grasses.add(outerGrass);
            emptyOuterJungle.remove(outerPosition);
        }

    }

    public void feed(AnimalsManager animalsManager) {
        ArrayList<Grass> newGrasses = new ArrayList<>();
        for(Grass grass : grasses) {
            if (!animalsManager.hasBeenEaten(grass.getWorldPosition(), config.plantEnergy))
                newGrasses.add(grass);
            else {
                Vector2 grassPosition = grass.getWorldPosition();
                if(config.jungleArea.contains(grassPosition))
                    emptyInnerJungle.add(grassPosition);
                else
                    emptyOuterJungle.add(grassPosition);
            }

        }
        grasses = newGrasses;
    }

    public int getGrassCount() {
        return grasses.size();
    }

    public int getEmptySpots() {
        return  getJungleEmptySpots() + getOuterJungleEmptySpots();
    }

    public int getJungleEmptySpots() {
        return emptyInnerJungle.size();
    }

    public int getOuterJungleEmptySpots() {
        return emptyOuterJungle.size();
    }

    @Override
    protected void drawPawn(PApplet context) {
        for(Grass grass : grasses)
            grass.draw(context);
    }
}
