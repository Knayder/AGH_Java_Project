package test.agh.cs.lab2.utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import main.agh.cs.lab2.utility.MoveDirection;

class MoveDirectionTest {
    @Test
    void get_right_rotations() {
        assertEquals(MoveDirection.FORWARD.get_right_rotations(), 0);
        assertEquals(MoveDirection.RIGHT.get_right_rotations(), 1);
        assertEquals(MoveDirection.BACKWARD.get_right_rotations(), 2);
        assertEquals(MoveDirection.LEFT.get_right_rotations(), 3);
    }
}
