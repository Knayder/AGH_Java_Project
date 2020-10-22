package agh.cs.lab2.utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class MoveDirectionTest {
    @Test
    void getRightRotations() {
        assertEquals(MoveDirection.FORWARD.getRightRotations(), 0);
        assertEquals(MoveDirection.RIGHT.getRightRotations(), 1);
        assertEquals(MoveDirection.BACKWARD.getRightRotations(), 2);
        assertEquals(MoveDirection.LEFT.getRightRotations(), 3);
    }
}
