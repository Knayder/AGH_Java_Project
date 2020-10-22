package agh.cs.lab2;

import agh.cs.lab2.utility.MoveDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class AnimalTest {

    @Test
    void move() {
        Animal animal = new Animal();
        assertEquals(animal.toString(), "(2, 2), Północ");
        animal.move(MoveDirection.FORWARD);
        assertEquals(animal.toString(), "(2, 3), Północ");
        animal.move(MoveDirection.RIGHT);
        assertEquals(animal.toString(), "(3, 3), Wschód");
        animal.move(MoveDirection.FORWARD);
        assertEquals(animal.toString(), "(4, 3), Wschód");
        animal.move(MoveDirection.FORWARD);
        assertEquals(animal.toString(), "(4, 3), Wschód");

        animal.move(MoveDirection.LEFT);
        assertEquals(animal.toString(), "(4, 4), Północ");
        animal.move(MoveDirection.FORWARD);
        assertEquals(animal.toString(), "(4, 4), Północ");

        animal.move(MoveDirection.LEFT);
        assertEquals(animal.toString(), "(3, 4), Zachód");
        animal.move(MoveDirection.FORWARD);
        assertEquals(animal.toString(), "(2, 4), Zachód");
        animal.move(MoveDirection.FORWARD);
        assertEquals(animal.toString(), "(1, 4), Zachód");
        animal.move(MoveDirection.FORWARD);
        assertEquals(animal.toString(), "(0, 4), Zachód");
        animal.move(MoveDirection.FORWARD);
        assertEquals(animal.toString(), "(0, 4), Zachód");

        animal.move(MoveDirection.LEFT);
        assertEquals(animal.toString(), "(0, 3), Południe");
        animal.move(MoveDirection.BACKWARD);
        assertEquals(animal.toString(), "(0, 4), Południe");

        animal.move(MoveDirection.BACKWARD);
        assertEquals(animal.toString(), "(0, 4), Południe");
        animal.move(MoveDirection.FORWARD);
        assertEquals(animal.toString(), "(0, 3), Południe");
        animal.move(MoveDirection.FORWARD);
        assertEquals(animal.toString(), "(0, 2), Południe");
        animal.move(MoveDirection.FORWARD);
        assertEquals(animal.toString(), "(0, 1), Południe");
        animal.move(MoveDirection.FORWARD);
        assertEquals(animal.toString(), "(0, 0), Południe");
        animal.move(MoveDirection.RIGHT);
        assertEquals(animal.toString(), "(0, 0), Południe");
    }
}