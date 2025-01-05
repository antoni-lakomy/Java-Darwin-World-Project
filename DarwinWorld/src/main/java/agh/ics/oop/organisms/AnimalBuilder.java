package agh.ics.oop.organisms;

import agh.ics.oop.enums.MapDirection;
import agh.ics.oop.records.SimParams;
import agh.ics.oop.records.Vector2d;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class AnimalBuilder {

    private final Random rng;

    private final int genomeLength;

    private final int directions;

    private final int reproductionCost;

    private final int maxMutation;

    private final int minMutation;

    public AnimalBuilder(SimParams params){
        this.rng = new Random(params.seed());
        this.genomeLength = params.animalGenomeLength();
        this.directions = MapDirection.values().length;
        this.reproductionCost = params.animalReproductionCost();
        this.minMutation = params.animalMinMutation();
        this.maxMutation = params.animalMaxMutation();
    }

    public Animal buildBase(Vector2d position, int energy, Byte[] genome){

        Animal animal = new Animal(position);
        animal.orientation = MapDirection.values()[rng.nextInt(directions)];

        animal.energy = energy;
        animal.age = 0;

        animal.currentGene = rng.nextInt(genomeLength);
        animal.genome = genome;

        animal.children = new LinkedList<>();

        return  animal;
    }

    /**
     * Creates a fresh new animal at a given position with a given energy.
     * The resulting animal has a random genome, is turned in a random direction,
     * and has a random gene active. It also has no parents and no children.
     * Its age is also set to 0.
     *
     * @param position The starting position of the new animal.
     * @param energy The starting energy of the new animal.
     *
     * @return A new Animal object with the given specs.
     */
    public Animal buildFresh(Vector2d position, int energy){

        Byte[] freshGenome = new Byte[genomeLength];
        for (int i = 0; i < genomeLength; i++){
            freshGenome[i] = (byte)rng.nextInt(directions);
        }

        Animal animal = buildBase(position,energy,freshGenome);

        animal.parents = new ArrayList<>();

        return animal;
    }


    /**
     * Creates a new animal based on the information of the two provided parents.
     * The resulting animal is placed in the same position as its parents.
     * Its genome is also a mix of its parents', with random mutations
     * as specified in simulation parameters.
     * Its energy is drawn from its parents as specified.
     * It is turned in a random direction and has a random gene active.
     * It also has no children and its age is set to 0.
     *
     * @param parentStrong The stronger of the two parents.
     * @param parentWeak The weaker of the two parents.
     *
     * @return A new Animal object - the child of the provided parents.
     */
    public Animal buildFromParents(Animal parentStrong, Animal parentWeak){
        boolean strongLeft = rng.nextBoolean();

        Byte[] newGenome = new Byte[genomeLength];

        int splitPointLeft = Math.round(parentStrong.getEnergy()
                                    / (float)(parentStrong.getEnergy() + parentWeak.getEnergy())
                                    * genomeLength);

        int splitPointRight = genomeLength - splitPointLeft;
        Byte[] sGenome = parentStrong.getGenome();
        Byte[] wGenome = parentWeak.getGenome();

        if (strongLeft){
            System.arraycopy(sGenome, 0, newGenome, 0, splitPointLeft);
            System.arraycopy(wGenome, splitPointLeft, newGenome, splitPointLeft,splitPointRight);
        } else{
            System.arraycopy(wGenome, 0, newGenome, 0, splitPointRight);
            System.arraycopy(sGenome, splitPointRight, newGenome, splitPointRight,splitPointLeft);
        }

        int mutation = 0;
        if (maxMutation > 0 ) mutation = rng.nextInt(minMutation,maxMutation+1);

        for (int i = 0; i < mutation; i++){
            newGenome[rng.nextInt(genomeLength)] = (byte)rng.nextInt(directions);
        }

        Animal animal = buildBase(parentStrong.getPosition(),reproductionCost*2,newGenome);

        //still not sure if it should be here
        parentStrong.energy -= reproductionCost;
        parentWeak.energy -= reproductionCost;
        parentStrong.children.add(animal);
        parentWeak.children.add(animal);

        animal.parents = List.of(parentStrong,parentWeak);

        return animal;
    }

}
