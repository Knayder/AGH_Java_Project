package agh.cs.lab2;

import agh.cs.lab2.utility.MapVisualizer;
import agh.cs.lab2.utility.MoveDirection;
import agh.cs.lab2.utility.Vector2d;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWorldMap implements IWorldMap {
    public AbstractWorldMap() {
        this.animals = new ArrayList<Animal>();
        this.mapVisualizer = new MapVisualizer(this);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }
    @Override
    public void run(MoveDirection[] directions) {
        int size = animals.size();
        if (size == 0)
            return;
        for(int i = 0; i<directions.length; ++i)
            animals.get(i % size).move(directions[i]);
    }
    @Override
    public boolean place(Animal animal) {
        if( canMoveTo(animal.getPosition()) ) {
            animals.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        Vector2d[] corners = getBoundaries();
        return mapVisualizer.draw(corners[0], corners[1]);
    }

    protected abstract Vector2d[] getBoundaries();  // lepiej użyć Pair<>, bo wymusza odpowiednią liczbę zwróconych wartości


    protected List<Animal> animals;
    protected MapVisualizer mapVisualizer;
}
