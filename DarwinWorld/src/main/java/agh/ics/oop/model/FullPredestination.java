package agh.ics.oop.model;

import agh.ics.oop.organisms.Animal;

/**
 * Always returns the next gene after the current one without skipping anything.
 */
public class FullPredestination implements GeneInterpreter{

    @Override
    public int getNextGene(Animal animal) {
        return (animal.getCurrentGene() + 1) % animal.getGenome().length;
    }
}
