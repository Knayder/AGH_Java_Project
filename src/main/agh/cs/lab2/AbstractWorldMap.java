package agh.cs.lab2;

import agh.cs.lab2.utility.IPositionChangeObserver;
import agh.cs.lab2.utility.MapVisualizer;
import agh.cs.lab2.utility.MoveDirection;
import agh.cs.lab2.utility.Vector2d;

import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    public AbstractWorldMap() {
        this.animals = new HashMap<Vector2d, Animal>();
        this.mapVisualizer = new MapVisualizer(this);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        animals.put(newPosition, animals.remove(oldPosition));
    }

    @Override
    public void run(MoveDirection[] directions) {
        Object[] animals_arr = animals.values().toArray();
        int size = animals_arr.length;

        for(int i = 0; i<directions.length; ++i) {

            Animal animal = (Animal)animals_arr[i%size];
            animal.move(directions[i]);

            //animals.remove(animal.getPosition());
            //animal.move(directions[i]);
            //animals.put(animal.getPosition(), animal);
        }
    }
    @Override
    public boolean place(Animal animal) {
        if( canMoveTo(animal.getPosition()) ) {
            animals.put(animal.getPosition(), animal);
            animal.addObserver((IPositionChangeObserver)this);
            return true;
        }
        throw new IllegalArgumentException("Cannot place animal");
    }

    @Override
    public String toString() {
        Vector2d[] corners = getBoundaries();
        return mapVisualizer.draw(corners[0], corners[1]);
    }

    protected abstract Vector2d[] getBoundaries();

    protected Map<Vector2d, Animal> animals;
    //protected List<Animal> animals;
    protected MapVisualizer mapVisualizer;
}
