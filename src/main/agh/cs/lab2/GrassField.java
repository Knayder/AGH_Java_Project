package agh.cs.lab2;

import agh.cs.lab2.utility.IPositionChangeObserver;
import agh.cs.lab2.utility.MapBoundary;
import agh.cs.lab2.utility.Vector2d;

import java.util.*;

import static java.lang.StrictMath.sqrt;

public class GrassField extends AbstractWorldMap {
    public GrassField(int grass_amount) {
        super();
        int size = (int)sqrt(grass_amount * 10);

        this.grass = new ArrayList<Grass>();

        mapBoundary = new MapBoundary();

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
    public boolean place(Animal animal) {
        if( canMoveTo(animal.getPosition()) ) {
            animals.put(animal.getPosition(), animal);
            mapBoundary.animalPlaced(animal);
            animal.addObserver((IPositionChangeObserver)mapBoundary);
            animal.addObserver((IPositionChangeObserver)this);
            return true;
        }
        throw new IllegalArgumentException("Cannot place animal");
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return animals.get(position) == null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        Animal animal = animals.get(position);
        if(animal != null)
            return animal;
        for(Grass gr : grass)
            if (gr.getPosition().equals(position))
                return gr;
        return null;
    }

    @Override
    protected Vector2d[] getBoundaries() {
        return new Vector2d[]{mapBoundary.getLowerLeft(), mapBoundary.getUpperRight()};
        /*Vector2d lowerLeftCorner = new Vector2d(0,0);
        Vector2d upperRightCorner = new Vector2d(0,0);
        Iterator<Map.Entry<Vector2d, Animal>> it = animals.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<Vector2d, Animal> animal = it.next();
            if(animal != null && animal.getValue() != null) {
                lowerLeftCorner = lowerLeftCorner.lowerLeft(animal.getValue().getPosition());
                upperRightCorner = upperRightCorner.upperRight(animal.getValue().getPosition());
            }
        }
        for(Grass gr : grass) {
            lowerLeftCorner = lowerLeftCorner.lowerLeft(gr.getPosition());
            upperRightCorner = upperRightCorner.upperRight(gr.getPosition());
        }
        return new Vector2d[]{lowerLeftCorner, upperRightCorner};*/
    }

    private MapBoundary mapBoundary;
    private List<Grass> grass;

}
