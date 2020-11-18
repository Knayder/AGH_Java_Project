package agh.cs.lab2.utility;

public interface IPositionChangeObserver {
    void positionChanged(Vector2d oldPosition, Vector2d newPosition);
}
