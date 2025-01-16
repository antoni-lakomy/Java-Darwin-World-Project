package agh.ics.oop.enums;

import agh.ics.oop.records.Vector2d;

public enum MapDirection {
    NORTH,
    NORTH_EAST,
    EAST,
    SOUTH_EAST,
    SOUTH,
    SOUTH_WEST,
    WEST,
    NORTH_WEST;

    @Override
    public String toString(){
        return switch (this){
            case NORTH -> "^";
            case NORTH_EAST -> "1";
            case EAST -> ">";
            case SOUTH_EAST -> "2";
            case SOUTH -> "v";
            case SOUTH_WEST -> "3";
            case WEST -> "<";
            case NORTH_WEST -> "4";
        };
    }

    /**
     * Returns a MapDirection rotated clockwise by a given rotation.
     *
     * @param rotation Number of 45 degree steps to rotate by.
     * @return A new rotated MapDirection.
     */
    public MapDirection rotate(int rotation){
        return MapDirection.values()[(rotation + this.ordinal()) % 8];
    }

    /**
     * Converts this MapDirection to a corresponding vector on a 2d plane.
     *
     * @return A new Vector2d representing this direction.
     */
    public Vector2d toUnitVector(){
        return switch (this){
            case NORTH -> new Vector2d(0,1);
            case NORTH_EAST -> new Vector2d(1,1);
            case EAST -> new Vector2d(1,0);
            case SOUTH_EAST -> new Vector2d(1,-1);
            case SOUTH -> new Vector2d(0,-1);
            case SOUTH_WEST -> new Vector2d(-1,-1);
            case WEST -> new Vector2d(-1,0);
            case NORTH_WEST -> new Vector2d(-1,1);
        };
    }

}

