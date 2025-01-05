package agh.ics.oop.organisms;

import agh.ics.oop.records.Vector2d;

public abstract class AbstractOrganism {
    protected Vector2d position;

    public AbstractOrganism(Vector2d position) {
        this.position = position;
    }

    public Vector2d getPosition() {
        return position;
    }

    public void setPosition(Vector2d position) {
        this.position = position;
    }

    public abstract void interact(AbstractOrganism other);
    // leaves space for any future interaction implementation
}