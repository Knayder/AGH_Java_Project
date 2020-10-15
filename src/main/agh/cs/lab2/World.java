package main.agh.cs.lab2;


import main.agh.cs.lab2.utility.MoveDirection;

public class World {
    public static void main(String[] args) {
        Animal animal = new Animal();
        MoveDirection[] dirs = OptionsParser.parse(new String[]{"r", "forward", "f", "f"});
        for(MoveDirection dir : dirs) {
            animal.move(dir);
        }
        System.out.println(animal);
    }
}
