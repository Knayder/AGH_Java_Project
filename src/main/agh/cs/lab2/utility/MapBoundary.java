package agh.cs.lab2.utility;

import agh.cs.lab2.Animal;

import java.util.ArrayList;

public class MapBoundary implements IPositionChangeObserver {
    public MapBoundary() {
        x_axis = new ArrayList<>();
        y_axis = new ArrayList<>();
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        sort();
    }

    public Vector2d getLowerLeft() {
        if(x_axis.size() > 0 && y_axis.size() > 0) {
            return new Vector2d(
                    x_axis.get(0).getPosition().x,
                    y_axis.get(0).getPosition().y
            );
        }
        return new Vector2d(0, 0);
    }

    public Vector2d getUpperRight() {
        int size = x_axis.size();
        if(size > 0 && size == y_axis.size()) {
            return new Vector2d(
                    x_axis.get(size-1).getPosition().x,
                    y_axis.get(size-1).getPosition().y
            );
        }
        return new Vector2d(10, 10);
    }

    public void animalPlaced(Animal animal) {
        x_axis.add(animal);
        y_axis.add(animal);
        sort();
    }

    private void sort() {
        x_axis.sort((p1, p2) -> p1.getPosition().x - p2.getPosition().x);
        y_axis.sort((p1, p2) -> p1.getPosition().y - p2.getPosition().y);
    }

    private ArrayList<Animal> x_axis;
    private ArrayList<Animal> y_axis;
}
