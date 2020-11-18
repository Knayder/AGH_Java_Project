package agh.cs.lab2;

import agh.cs.lab2.utility.*;

import java.util.ArrayList;

public class Animal extends AbstractMapElement {

    public Animal(IWorldMap map, Vector2d initialPosition) {
        super(initialPosition);
        this.mapDirection = MapDirection.NORTH;
        observers = new ArrayList<>();
        if (map.place(this))
            this.worldMap = map;
        else
            throw new IllegalArgumentException("Cannot place animal on that position");


    }

    public Animal(IWorldMap map) {
        this(map, new Vector2d(2,2));
    }

    void addObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }

    void removeObserver(IPositionChangeObserver observer) {
        observers.remove(observer);
    }

    void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for(IPositionChangeObserver observer : observers)
            observer.positionChanged(oldPosition, newPosition);
    }

    public void move(MoveDirection direction) {
        MapDirection newMapDirection = mapDirection.getRelativeDirection(direction);

        if(direction == MoveDirection.FORWARD || direction == MoveDirection.BACKWARD) {
            Vector2d newPosition = position.add(newMapDirection.toUnitVector());
            if(worldMap != null && !worldMap.canMoveTo(newPosition))
                return;
            Vector2d oldPosition = position;
            position = new Vector2d(newPosition.x, newPosition.y);
            positionChanged(oldPosition, position);
        }

        if (direction != MoveDirection.BACKWARD)
            mapDirection = newMapDirection;
    }

    @Override
    protected String stringRepresentation() {
        switch (this.mapDirection) {
            case WEST: return "<";
            case EAST: return ">";
            case SOUTH: return "v";
            case NORTH: default: return "^";
        }
    }

    private MapDirection mapDirection;
    private final IWorldMap worldMap;
    private ArrayList<IPositionChangeObserver> observers;
}
