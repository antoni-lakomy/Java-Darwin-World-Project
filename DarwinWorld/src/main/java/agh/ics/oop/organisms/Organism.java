package agh.ics.oop.organisms;

import agh.ics.oop.records.Vector2d;

public class Organism {

    protected Vector2d position;

    public Organism(Vector2d position) {
        this.position = position;
    }

    public Vector2d getPosition() {
        return position;
    }

    public void setPosition(Vector2d position) {
        this.position = position;
    }

}