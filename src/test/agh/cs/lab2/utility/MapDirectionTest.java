package agh.cs.lab2.utility;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MapDirectionTest {

    @Test
    void to_unit_vector() {
        assertEquals( (MapDirection.NORTH).to_unit_vector(), new Vector2d(0, 1) );
        assertEquals( (MapDirection.SOUTH).to_unit_vector(), new Vector2d(0, -1) );
        assertEquals( (MapDirection.EAST).to_unit_vector(), new Vector2d(1, 0) );
        assertEquals( (MapDirection.WEST).to_unit_vector(), new Vector2d(-1, 0) );
    }

    @Test
    void next() {
        assertEquals((MapDirection.NORTH).next(), MapDirection.EAST);
        assertEquals((MapDirection.EAST).next(), MapDirection.SOUTH);
        assertEquals((MapDirection.SOUTH).next(), MapDirection.WEST);
        assertEquals((MapDirection.WEST).next(), MapDirection.NORTH);
    }

    @Test
    void previous() {
        assertEquals((MapDirection.NORTH).previous(), MapDirection.WEST);
        assertEquals((MapDirection.WEST).previous(), MapDirection.SOUTH);
        assertEquals((MapDirection.SOUTH).previous(), MapDirection.EAST);
        assertEquals((MapDirection.EAST).previous(), MapDirection.NORTH);
    }

    @Test
    void testToString() {
        assertEquals((MapDirection.NORTH).toString(), "Północ");
        assertEquals((MapDirection.WEST).toString(), "Zachód");
        assertEquals((MapDirection.SOUTH).toString(), "Południe");
        assertEquals((MapDirection.EAST).toString(), "Wschód");
    }

    @Test
    void get_relative_direction() {
        assertEquals(MapDirection.NORTH.get_relative_direction(MoveDirection.FORWARD), MapDirection.NORTH);
        assertEquals(MapDirection.NORTH.get_relative_direction(MoveDirection.RIGHT), MapDirection.EAST);
        assertEquals(MapDirection.NORTH.get_relative_direction(MoveDirection.BACKWARD), MapDirection.SOUTH);
        assertEquals(MapDirection.NORTH.get_relative_direction(MoveDirection.LEFT), MapDirection.WEST);

        assertEquals(MapDirection.EAST.get_relative_direction(MoveDirection.FORWARD), MapDirection.EAST);
        assertEquals(MapDirection.EAST.get_relative_direction(MoveDirection.RIGHT), MapDirection.SOUTH);
        assertEquals(MapDirection.EAST.get_relative_direction(MoveDirection.BACKWARD), MapDirection.WEST);
        assertEquals(MapDirection.EAST.get_relative_direction(MoveDirection.LEFT), MapDirection.NORTH);

        assertEquals(MapDirection.SOUTH.get_relative_direction(MoveDirection.FORWARD), MapDirection.SOUTH);
        assertEquals(MapDirection.SOUTH.get_relative_direction(MoveDirection.RIGHT), MapDirection.WEST);
        assertEquals(MapDirection.SOUTH.get_relative_direction(MoveDirection.BACKWARD), MapDirection.NORTH);
        assertEquals(MapDirection.SOUTH.get_relative_direction(MoveDirection.LEFT), MapDirection.EAST);

        assertEquals(MapDirection.WEST.get_relative_direction(MoveDirection.FORWARD), MapDirection.WEST);
        assertEquals(MapDirection.WEST.get_relative_direction(MoveDirection.RIGHT), MapDirection.NORTH);
        assertEquals(MapDirection.WEST.get_relative_direction(MoveDirection.BACKWARD), MapDirection.EAST);
        assertEquals(MapDirection.WEST.get_relative_direction(MoveDirection.LEFT), MapDirection.SOUTH);
    }
}