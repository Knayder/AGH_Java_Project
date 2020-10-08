package agh.cs.lab2.utility;

public enum MapDirection {
    NORTH, SOUTH, WEST, EAST;


    public Vector2d to_unit_vector() {
        switch(this) {
            case WEST: return new Vector2d(-1, 0);
            case EAST: return new Vector2d(1, 0);
            case SOUTH: return new Vector2d(0, -1);
        }
        return new Vector2d(0, 1);
    }

    public MapDirection next() {
        switch(this) {
            case NORTH: return EAST;
            case EAST: return SOUTH;
            case SOUTH: return WEST;
        }
        return NORTH;
    }

    public MapDirection previous() {
        switch(this) {
            case NORTH: return WEST;
            case WEST: return SOUTH;
            case SOUTH: return EAST;
        }
        return NORTH;
    }

    @Override
    public String toString() {
        switch(this) {
            case EAST: return "Wschód";
            case SOUTH: return "Południe";
            case WEST: return "Zachód";
        }
        return "Północ";
    }
}
