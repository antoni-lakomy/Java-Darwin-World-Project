package agh.ics.oop.organisms;

import agh.ics.oop.enums.MapDirection;
import agh.ics.oop.records.Vector2d;

import java.util.List;

public class Animal extends Organism{

    protected MapDirection orientation;

    protected Byte[] genome;

    protected int currentGene;

    protected int energy;

    protected int age;

    protected List<Animal> parents;

    protected List<Animal> children;

    public Byte[] getGenome() { return genome.clone(); }

    public MapDirection getOrientation() { return  orientation; }

    public int getCurrentGene() { return  currentGene; }

    public int getEnergy() { return energy; }

    public int getAge() {return age; }

    public List<Animal> getParents() { return parents; }

    public List<Animal> getChildren() { return children;}

    public Animal(Vector2d position){
        super(position);
    }

    public void addEnergy(int energy){
        this.energy += energy;
    }

    @Override
    public String toString(){
        return orientation.toString();
    }

    //TODO - modularna losowość ze starości nie radości
    public Vector2d activateGene(){
        orientation = orientation.rotate(genome[currentGene]);
        currentGene = (currentGene + 1) % genome.length;
        return orientation.toUnitVector();
    }

    public void setPosition(Vector2d position){
        this.position = position;
    }

}
