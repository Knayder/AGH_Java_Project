package agh.cs.lab2.utility;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    @Test
    void precedes() {
        assertTrue((new Vector2d(1,2)).precedes(new Vector2d(3,4)));
        assertTrue((new Vector2d(1,2)).precedes(new Vector2d(1,2)));
        assertTrue((new Vector2d(1,2)).precedes(new Vector2d(1,4)));
        assertTrue((new Vector2d(1,2)).precedes(new Vector2d(3,2)));
        assertFalse((new Vector2d(1,2)).precedes(new Vector2d(3,1)));
    }

    @Test
    void follows() {
        assertTrue((new Vector2d(3,4)).follows(new Vector2d(1, 2)));
        assertTrue((new Vector2d(1, 2)).follows(new Vector2d(1, 2)));
        assertTrue((new Vector2d(1, 4)).follows(new Vector2d(1, 2)));
        assertTrue((new Vector2d(3, 2)).follows(new Vector2d(1, 2)));
    }

    @Test
    void upper_right() {
        assertEquals(  (new Vector2d(1,4)).upper_right(new Vector2d(3, 2)), new Vector2d(3, 4)  );
        assertEquals(  (new Vector2d(8,3)).upper_right(new Vector2d(4, 5)), new Vector2d(8, 5)  );
        assertEquals(  (new Vector2d(2,7)).upper_right(new Vector2d(7, 9)), new Vector2d(7, 9)  );
        assertEquals(  (new Vector2d(8,1)).upper_right(new Vector2d(4, 1)), new Vector2d(8, 1)  );
    }

    @Test
    void lower_left() {
        assertEquals(  (new Vector2d(1,4)).lower_left(new Vector2d(3, 2)), new Vector2d(1, 2)  );
        assertEquals(  (new Vector2d(8,3)).lower_left(new Vector2d(4, 5)), new Vector2d(4, 3)  );
        assertEquals(  (new Vector2d(2,7)).lower_left(new Vector2d(7, 9)), new Vector2d(2, 7)  );
        assertEquals(  (new Vector2d(8,1)).lower_left(new Vector2d(4, 1)), new Vector2d(4, 1)  );
    }

    @Test
    void add() {
        assertEquals(  (new Vector2d(1,4)).add(new Vector2d(3, 2)), new Vector2d(4, 6)  );
        assertEquals(  (new Vector2d(7,2)).add(new Vector2d(1, 8)), new Vector2d(8, 10)  );
        assertEquals(  (new Vector2d(4,4)).add(new Vector2d(5, 1)), new Vector2d(9, 5)  );
        assertEquals(  (new Vector2d(2,8)).add(new Vector2d(8, 4)), new Vector2d(10, 12)  );
    }

    @Test
    void subtract() {
        assertEquals(  (new Vector2d(1,4)).subtract(new Vector2d(3, 2)), new Vector2d(-2, 2)  );
        assertEquals(  (new Vector2d(7,2)).subtract(new Vector2d(1, 8)), new Vector2d(6, -6)  );
        assertEquals(  (new Vector2d(4,4)).subtract(new Vector2d(5, 1)), new Vector2d(-1, 3)  );
        assertEquals(  (new Vector2d(2,8)).subtract(new Vector2d(8, 4)), new Vector2d(-6, 4)  );
    }

    @Test
    void opposite() {
        assertEquals( (new Vector2d(1, 2)).opposite(), new Vector2d(-1, -2) );
        assertEquals( (new Vector2d(8, 7)).opposite(), new Vector2d(-8, -7) );
        assertEquals( (new Vector2d(3, 3)).opposite(), new Vector2d(-3, -3) );
        assertEquals( (new Vector2d(5, 9)).opposite(), new Vector2d(-5, -9) );
    }

    @Test
    void testToString() {
        assertEquals( (new Vector2d(1, 2)).toString(), "(1, 2)");
        assertEquals( (new Vector2d(8, 7)).toString(), "(8, 7)");
        assertEquals( (new Vector2d(3, 3)).toString(), "(3, 3)");
        assertEquals( (new Vector2d(5, 9)).toString(), "(5, 9)");
    }

    @Test
    void testEquals() {
        assertEquals( (new Vector2d(1, 2)).equals(new Vector2d(1, 2)), true);
        assertEquals( (new Vector2d(1, 2)).equals(new Vector2d(2, 2)), false);
        assertEquals( (new Vector2d(-8, 2)).equals(new Vector2d(-8, 2)), true);
        assertEquals( (new Vector2d(8, 2)).equals(new Vector2d(-8, 2)), false);
    }
}