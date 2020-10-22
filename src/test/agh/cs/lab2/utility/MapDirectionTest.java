package agh.cs.lab2.utility;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MapDirectionTest {

    @Test
    void toUnitVector() {
        assertEquals( (MapDirection.NORTH).toUnitVector(), new Vector2d(0, 1) );
        assertEquals( (MapDirection.SOUTH).toUnitVector(), new Vector2d(0, -1) );
        assertEquals( (MapDirection.EAST).toUnitVector(), new Vector2d(1, 0) );
        assertEquals( (MapDirection.WEST).toUnitVector(), new Vector2d(-1, 0) );
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
    void getRelativeDirection() {
        assertEquals(MapDirection.NORTH.getRelativeDirection(MoveDirection.FORWARD), MapDirection.NORTH);
        assertEquals(MapDirection.NORTH.getRelativeDirection(MoveDirection.RIGHT), MapDirection.EAST);
        assertEquals(MapDirection.NORTH.getRelativeDirection(MoveDirection.BACKWARD), MapDirection.SOUTH);
        assertEquals(MapDirection.NORTH.getRelativeDirection(MoveDirection.LEFT), MapDirection.WEST);

        assertEquals(MapDirection.EAST.getRelativeDirection(MoveDirection.FORWARD), MapDirection.EAST);
        assertEquals(MapDirection.EAST.getRelativeDirection(MoveDirection.RIGHT), MapDirection.SOUTH);
        assertEquals(MapDirection.EAST.getRelativeDirection(MoveDirection.BACKWARD), MapDirection.WEST);
        assertEquals(MapDirection.EAST.getRelativeDirection(MoveDirection.LEFT), MapDirection.NORTH);

        assertEquals(MapDirection.SOUTH.getRelativeDirection(MoveDirection.FORWARD), MapDirection.SOUTH);
        assertEquals(MapDirection.SOUTH.getRelativeDirection(MoveDirection.RIGHT), MapDirection.WEST);
        assertEquals(MapDirection.SOUTH.getRelativeDirection(MoveDirection.BACKWARD), MapDirection.NORTH);
        assertEquals(MapDirection.SOUTH.getRelativeDirection(MoveDirection.LEFT), MapDirection.EAST);

        assertEquals(MapDirection.WEST.getRelativeDirection(MoveDirection.FORWARD), MapDirection.WEST);
        assertEquals(MapDirection.WEST.getRelativeDirection(MoveDirection.RIGHT), MapDirection.NORTH);
        assertEquals(MapDirection.WEST.getRelativeDirection(MoveDirection.BACKWARD), MapDirection.EAST);
        assertEquals(MapDirection.WEST.getRelativeDirection(MoveDirection.LEFT), MapDirection.SOUTH);
    }
}