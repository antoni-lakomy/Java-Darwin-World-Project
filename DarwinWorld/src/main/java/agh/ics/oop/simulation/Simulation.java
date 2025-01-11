package agh.ics.oop.simulation;

import agh.ics.oop.maps.WorldMap;
import agh.ics.oop.organisms.Animal;
import agh.ics.oop.organisms.AnimalBuilder;
import agh.ics.oop.planters.Planter;

import java.util.List;

public class Simulation {

    protected WorldMap map;

    protected Planter planter;

    protected AnimalBuilder animalBuilder;

    protected List<Animal> aliveAnimals;

    //TODO
    protected List<Animal> deadAnimals;


    public Simulation(WorldMap map, Planter planter, AnimalBuilder animalBuilder, List<Animal> animals){

    }

}
