package main.agh.cs.lab2.utility;

public enum MoveDirection {
    FORWARD, RIGHT, BACKWARD, LEFT;

    public int get_right_rotations() {
        return ordinal();
    }
}