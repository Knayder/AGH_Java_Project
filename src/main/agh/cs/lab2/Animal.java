package agh.cs.lab2;

import agh.cs.lab2.utility.*;

public class Animal {
    public Animal() {
        this.mapDirection = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
    }

    public void move(MoveDirection direction) {
        MapDirection newMapDirection = mapDirection.getRelativeDirection(direction);
        Vector2d newPosition = position.add(newMapDirection.toUnitVector());
        if (newPosition.x >= 0 && newPosition.x <= 4 && newPosition.y >= 0 && newPosition.y <= 4) {
            position = newPosition;
            if (direction != MoveDirection.BACKWARD)
                mapDirection = newMapDirection;
        }

    }

    @Override
    public String toString() {
        return position.toString() + ", " + mapDirection.toString();
    }

    private MapDirection mapDirection;
    private Vector2d position;
}
