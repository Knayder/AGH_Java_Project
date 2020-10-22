package agh.cs.lab2;


import agh.cs.lab2.utility.MoveDirection;
import agh.cs.lab2.utility.OptionsParser;
import agh.cs.lab2.utility.Vector2d;

public class World {
    public static void main(String[] args) {
        MoveDirection[] directions = OptionsParser.parse(new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"});
        IWorldMap map = new RectangularMap(10, 5);
        new Animal(map);
        new Animal(map,new Vector2d(3,4));

        System.out.println(map);
        map.run(directions);

        System.out.println(map);
    }
}
