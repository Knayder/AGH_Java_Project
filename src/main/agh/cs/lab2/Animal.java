package agh.cs.lab2;

import agh.cs.lab2.utility.*;

public class Animal {
    public Animal() {
        this.map_direction = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
    }

    public void move(MoveDirection direction) {
        MapDirection new_map_direction = map_direction.get_relative_direction(direction);
        Vector2d new_position = position.add(new_map_direction.to_unit_vector());
        if (new_position.x >= 0 && new_position.x <= 4 && new_position.y >= 0 && new_position.y <= 4) {
            position = new_position;
            map_direction = new_map_direction;
        }

    }

    @Override
    public String toString() {
        return "(" + position.x + ", " + position.y + "), " + map_direction.toString();
    }

    private MapDirection map_direction;
    private Vector2d position;
}
