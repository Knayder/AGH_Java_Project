package agh.cs.project.utility;


import java.util.Objects;

public class Vector2 {
    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2() {
        this(0,0);
    }

    public Vector2 add(Vector2 other) {
        return new Vector2(x + other.x, y + other.y);
    }
    public Vector2 sub(Vector2 other) {
        return new Vector2(x - other.x, y - other.y);
    }

    public Vector2 multiply(Vector2 other) {
        return new Vector2(x * other.x, y * other.y);
    }

    public Vector2 multiply(int a) {
        return new Vector2(x * a, y * a);
    }

    public Vector2 multiply(float a) {
        return new Vector2((int)((float)x * a), (int)((float)y * a));
    }

    public boolean smallerThan(Vector2 other) {
        return x <= other.x && y <= other.y;
    }

    public boolean biggerThan(Vector2 other) {
        return x >= other.x && y >= other.y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2 vector2 = (Vector2) o;
        return x == vector2.x &&
                y == vector2.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public int x;
    public int y;
}