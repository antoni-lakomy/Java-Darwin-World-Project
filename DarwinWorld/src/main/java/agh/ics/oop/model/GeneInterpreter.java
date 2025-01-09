package agh.ics.oop.model;

import agh.ics.oop.organisms.Animal;

public interface GeneInterpreter {


    /**
     * Returns the next gene to be activated on the given animal.
     *
     * @param animal The animal to consider.
     * @return The index of the next active gene.
     */
    int getNextGene(Animal animal);
}
