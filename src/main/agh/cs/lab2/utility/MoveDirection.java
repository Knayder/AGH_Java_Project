package agh.cs.lab2.utility;

public enum MoveDirection {
    FORWARD, RIGHT, BACKWARD, LEFT;

    public int getRightRotations() {
        return ordinal();
    }
}