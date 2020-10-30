package agh.cs.lab2;

import agh.cs.lab2.utility.Vector2d;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.StrictMath.sqrt;

public class GrassField extends AbstractWorldMap {
    public GrassField(int grass_amount) {
        super();
        int size = (int)sqrt(grass_amount * 10);

        this.grass = new ArrayList<Grass>();

        Random random = new Random();
        for(int i = 0; i<grass_amount; ++i) {
            Vector2d position;
            do {
                position = new Vector2d(
                        random.nextInt(size + 1),
                        random.nextInt(size + 1)
                );
            } while(isOccupied(position));
            grass.add(new Grass(position));
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        for(Animal animal : animals)
            if(animal.getPosition().equals(position))
                return false;
        return true;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for(Animal animal : animals)
            if (animal.getPosition().equals(position))
                return animal;
        for(Grass gr : grass)
            if (gr.getPosition().equals(position))
                return gr;
        return null;
    }

    @Override
    protected Vector2d[] getBoundaries() {
        Vector2d lowerLeftCorner = new Vector2d(0,0);
        Vector2d upperRightCorner = new Vector2d(0,0);
        for(Animal animal : animals) {
            lowerLeftCorner = lowerLeftCorner.lowerLeft(animal.getPosition());
            upperRightCorner = upperRightCorner.upperRight(animal.getPosition());
        }
        for(Grass gr : grass) {
            lowerLeftCorner = lowerLeftCorner.lowerLeft(gr.getPosition());
            upperRightCorner = upperRightCorner.upperRight(gr.getPosition());
        }
        return new Vector2d[]{lowerLeftCorner, upperRightCorner};
    }


    private List<Grass> grass;

}
