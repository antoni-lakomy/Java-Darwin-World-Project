package agh.ics.oop.model;

import agh.ics.oop.organisms.Animal;
import agh.ics.oop.organisms.AnimalBuilder;
import agh.ics.oop.organisms.Organism;
import agh.ics.oop.organisms.Plant;

import java.util.ArrayList;
import java.util.List;

public class WorldTile {

    private Plant plant = null;

    private final List<Animal> animals = new ArrayList<>();

    public boolean isEmpty(){
        return animals.isEmpty() && plant == null;
    }

    public Organism getOrganism() {
        if (animals.isEmpty()) return plant;
        return animals.getFirst();
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public boolean removeAnimal(Animal animal){
        return animals.remove(animal);
    }

    private void sortAnimals(){
        animals.sort(AnimalComparator.getInstance());
    }

    public void tryToConsumePlant(){
        if (animals.isEmpty()) return;
        sortAnimals();
        if (plant != null){
            animals.getFirst().addEnergy(plant.getEnergy());
            plant = null;
        }
    }

    public void tryToMultiply(AnimalBuilder builder, int fedThreshold){
        for (int i = 1; i < animals.size(); i += 2){

            if (animals.get(i).getEnergy() < fedThreshold) break;

            animals.add(builder.buildFromParents(animals.get(i-1),animals.get(i)));
        }
        sortAnimals();
    }


}
