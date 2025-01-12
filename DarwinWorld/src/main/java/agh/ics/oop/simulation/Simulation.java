package agh.ics.oop.simulation;

import agh.ics.oop.maps.WorldMap;
import agh.ics.oop.observers.SimObserver;
import agh.ics.oop.organisms.Animal;
import agh.ics.oop.organisms.AnimalBuilder;
import agh.ics.oop.planters.Planter;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    protected WorldMap map;

    protected Planter planter;

    protected AnimalBuilder animalBuilder;

    protected List<Animal> aliveAnimals;

    //TODO
    protected List<Animal> deadAnimals;

    protected int animalFedThreshold;

    protected int plantGrowth;

    protected Simulation(){}

    private final List<SimObserver> observers = new ArrayList<>();

    public void addObserver(SimObserver observer){
        observers.addLast(observer);
    }

    public boolean removeObserver(SimObserver observer){
        return observers.remove(observer);
    }

    private void simUpdated(){
        for (SimObserver observer : observers){
            observer.update(map);
        }
    }

    public void simulationStep(){
        simUpdated();

        //1
        List<Animal> deadThisStep = map.removeDead();
        aliveAnimals.removeAll(deadThisStep);
        deadAnimals.addAll(deadThisStep);

        //2
        for (Animal animal : aliveAnimals){
            map.moveAnimal(animal);
        }

        //3
        planter.consume();

        //4
        List<Animal> newThisStep = map.reproduceAnimals(animalBuilder,animalFedThreshold);
        aliveAnimals.addAll(newThisStep);

        //5
        planter.plant(plantGrowth);
    }
}
