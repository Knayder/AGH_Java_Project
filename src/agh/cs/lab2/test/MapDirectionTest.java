package agh.cs.lab2.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import agh.cs.lab2.utility.MapDirection;
import agh.cs.lab2.utility.Vector2d;

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
}