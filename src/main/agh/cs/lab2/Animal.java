package agh.cs.lab2;

import agh.cs.lab2.utility.*;

public class Animal {
    public Animal() {   // tego konstruktora nie powinno być
        this.mapDirection = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
        this.worldMap = null;
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {    // DRY
        this.mapDirection = MapDirection.NORTH;
        this.position = initialPosition;
        if (map.place(this))
            this.worldMap = map;
        else
            this.worldMap = null;   // lepiej rzucić wyjątek
    }

    public Animal(IWorldMap map) {
        this(map, new Vector2d(2,2));
    }



    public void move(MoveDirection direction) {
        MapDirection newMapDirection = mapDirection.getRelativeDirection(direction);
        Vector2d newPosition = position.add(newMapDirection.toUnitVector());    // A co z RIGHT i LEFT? One nie mają zmieniać pozycji zwierzęcia.
        if (worldMap == null || worldMap.canMoveTo(newPosition) ) {
            position = newPosition;
            if (direction != MoveDirection.BACKWARD)
                mapDirection = newMapDirection;
        }
    }

    public Vector2d getPosition() {
        return this.position;
    }

    @Override
    public String toString() {
        switch (this.mapDirection) {
            case WEST: return "<";
            case EAST: return ">";
            case SOUTH: return "v";
            case NORTH: default: return "^";
        }
    }

    private MapDirection mapDirection;
    private Vector2d position;
    private IWorldMap worldMap; // to może być finalne
}
