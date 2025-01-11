package agh.ics.oop.organisms;

import agh.ics.oop.records.Vector2d;

public class Plant extends Organism{

    private final int energy;

    public int getEnergy() { return energy; }

    @Override
    public String toString() {
        return "*";
    }

    public Plant(Vector2d position, int energy){
        super(position);
        this.energy = energy;
    }
}
