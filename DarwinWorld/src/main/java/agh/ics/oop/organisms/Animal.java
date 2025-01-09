package agh.ics.oop.organisms;

import agh.ics.oop.enums.MapDirection;
import agh.ics.oop.model.GeneInterpreter;
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

    /**
     * The first part of animals movement.
     * a) Activates the current gene changing the orientation.
     * b) Changes the current gene to the next one according to the provided {@link GeneInterpreter}.
     * c) Drains the specified amount of energy from the animal.
     * d) Returns the current orientation as a vector on a 2D grid.
     *
     * @param interpreter The interpreter used to set the next active gene.
     * @param energyRequired The energy this move drains from the animal.
     * @return A {@link Vector2d} representing the orientation of the animal.
     */
    public Vector2d activateGene(GeneInterpreter interpreter, int energyRequired){
        orientation = orientation.rotate(genome[currentGene]);
        currentGene = interpreter.getNextGene(this);
        return orientation.toUnitVector();
    }

    public void setPosition(Vector2d position){
        this.position = position;
    }

}
