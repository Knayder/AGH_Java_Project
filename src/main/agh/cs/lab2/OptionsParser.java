package main.agh.cs.lab2;

import main.agh.cs.lab2.utility.MoveDirection;

public class OptionsParser {
    public static MoveDirection[] parse(String[] strings) {
        MoveDirection[] directions = new MoveDirection[strings.length];
        int index = 0;
        for(String str : strings) {
            if (str.equals("f") || str.equals("forward"))   // można użyć switch
                directions[index++] = MoveDirection.FORWARD;
            else if (str.equals("r") || str.equals("right"))
                directions[index++] = MoveDirection.RIGHT;
            else if (str.equals("b") || str.equals("backward"))
                directions[index++] = MoveDirection.BACKWARD;
            else if (str.equals("l") || str.equals("left"))
                directions[index++] = MoveDirection.LEFT;
        }
        MoveDirection[] result = new MoveDirection[index];  // Arrays.copyOfRange
        for(int i = 0; i<index; ++i)
            result[i] = directions[i];
        return result;
    }
}
