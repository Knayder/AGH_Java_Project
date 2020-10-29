package agh.cs.lab2;

import agh.cs.lab2.IWorldMap;
import agh.cs.lab2.utility.MapVisualizer;
import agh.cs.lab2.utility.MoveDirection;
import agh.cs.lab2.utility.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap {
    public RectangularMap(int width, int height) {
        this.lowerLeftCorner = new Vector2d(0,0);
        this.upperRightCorner = new Vector2d(width, height);    // width - 1
        this.animals = new ArrayList<Animal>();

        this.mapVisualizer = new MapVisualizer(this);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return
            position.follows(lowerLeftCorner) &&
            position.precedes(upperRightCorner) &&
            !isOccupied(position);
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
    public void run(MoveDirection[] directions) {
        int size = animals.size();
        for(int i = 0; i<directions.length; ++i)
            animals.get(i % size).move(directions[i]);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for(Animal animal : animals)
            if (animal.getPosition().equals(position))
                return animal;
        return null;
    }

    @Override
    public String toString() {
        return mapVisualizer.draw(lowerLeftCorner, upperRightCorner);
    }

    private List<Animal> animals;   // właściwie wszystkie 4 mogą być finalne
    private Vector2d lowerLeftCorner;
    private Vector2d upperRightCorner;

    private MapVisualizer mapVisualizer;
}
