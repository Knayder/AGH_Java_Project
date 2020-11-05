package agh.cs.lab2;

import agh.cs.lab2.utility.MoveDirection;
import agh.cs.lab2.utility.OptionsParser;
import agh.cs.lab2.utility.Vector2d;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    @Test
    void run() {
        MoveDirection[] directions = OptionsParser.parse(new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"});
        IWorldMap map = new RectangularMap(10, 5);
        Animal animal1 = new Animal(map);
        Animal animal2 = new Animal(map,new Vector2d(3,4));
        map.run(directions);
        assertEquals( animal1.getPosition(), new Vector2d(2, 0) );
        assertEquals( animal2.getPosition(), new Vector2d(3, 5) );
    }
}