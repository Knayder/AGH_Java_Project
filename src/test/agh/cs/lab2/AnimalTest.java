package agh.cs.lab2;

import agh.cs.lab2.utility.MoveDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class AnimalTest {

    @Test
    void move() {
        Animal animal = new Animal();
        assertEquals(animal.toString(), "^");
        animal.move(MoveDirection.FORWARD);
        assertEquals(animal.toString(), "^");
        animal.move(MoveDirection.RIGHT);
        assertEquals(animal.toString(), ">");
        animal.move(MoveDirection.FORWARD);
        assertEquals(animal.toString(), ">");
        animal.move(MoveDirection.FORWARD);
        assertEquals(animal.toString(), ">");

        animal.move(MoveDirection.LEFT);
        assertEquals(animal.toString(), "^");
        animal.move(MoveDirection.FORWARD);
        assertEquals(animal.toString(), "^");

        animal.move(MoveDirection.LEFT);
        assertEquals(animal.toString(), "<");
        animal.move(MoveDirection.FORWARD);
        assertEquals(animal.toString(), "<");
        animal.move(MoveDirection.FORWARD);
        assertEquals(animal.toString(), "<");
        animal.move(MoveDirection.FORWARD);
        assertEquals(animal.toString(), "<");
        animal.move(MoveDirection.FORWARD);
        assertEquals(animal.toString(), "<");

        animal.move(MoveDirection.LEFT);
        assertEquals(animal.toString(), "v");
        animal.move(MoveDirection.BACKWARD);
        assertEquals(animal.toString(), "v");

        animal.move(MoveDirection.BACKWARD);
        assertEquals(animal.toString(), "v");
        animal.move(MoveDirection.FORWARD);
        assertEquals(animal.toString(), "v");
        animal.move(MoveDirection.FORWARD);
        assertEquals(animal.toString(), "v");
        animal.move(MoveDirection.FORWARD);
        assertEquals(animal.toString(), "v");
        animal.move(MoveDirection.FORWARD);
        assertEquals(animal.toString(), "v");
        animal.move(MoveDirection.RIGHT);
        assertEquals(animal.toString(), "<");
    }
}