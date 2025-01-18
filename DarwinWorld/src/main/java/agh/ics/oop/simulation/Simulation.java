package agh.ics.oop.simulation;

import agh.ics.oop.maps.AbstractWorldMap;
import agh.ics.oop.maps.WorldMap;
import agh.ics.oop.observers.SimObserver;
import agh.ics.oop.organisms.Animal;
import agh.ics.oop.organisms.AnimalBuilder;
import agh.ics.oop.planters.ForestedEquators;
import agh.ics.oop.planters.Planter;

import java.util.*;

public class Simulation {

    protected WorldMap map;

    protected Planter planter;

    protected AnimalBuilder animalBuilder;

    protected List<Animal> aliveAnimals;

    //TODO
    protected List<Animal> deadAnimals;

    protected Map<List<Byte>, Integer> genomeFrequency = new HashMap<>();

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

    public void updateGenomeFrequency(List<Animal> deadThisStep, List<Animal> newThisStep) {
        // Remove genome frequencies for dead animals
        for (Animal deadAnimal : deadThisStep) {
            List<Byte> genome = Arrays.asList(deadAnimal.getGenome());
            genomeFrequency.put(genome, genomeFrequency.getOrDefault(genome, 0) - 1);

            // Remove genome entry if count reaches zero
            if (genomeFrequency.get(genome) <= 0) {
                genomeFrequency.remove(genome);
            }
        }

        // Add genome frequencies for new animals
        for (Animal newAnimal : newThisStep) {
            List<Byte> genome = Arrays.asList(newAnimal.getGenome());
            genomeFrequency.put(genome, genomeFrequency.getOrDefault(genome, 0) + 1);
        }
    }


    public Byte[] findMostPopularGenome() {
        return genomeFrequency.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get() // Safe to use .get() as we assume genomes are always present
                .getKey()
                .toArray(new Byte[0]);
    }


    public int calcAvgEnergy() {
        int totalEnergy = 0;
        for (Animal animal : aliveAnimals) {
            totalEnergy += animal.getEnergy();
        }
        return totalEnergy / aliveAnimals.size();
    }

    public int calcAvgLifespan() {
        int totalLifespan = 0;
        for (Animal animal : deadAnimals) {
            totalLifespan += animal.getAge();
        }
        return totalLifespan / deadAnimals.size();
    }

    public int calcAvgChildren() {
        int totalChildren = 0;
        for (Animal animal : aliveAnimals) {
            totalChildren += animal.getChildren().size();
        }
        return totalChildren / aliveAnimals.size();
    }

    public List<Animal> getAnimalsWithGivenGenome(Byte[] genome) {
        List<Animal> animalsWithGenome = new ArrayList<>();
        for (Animal animal : aliveAnimals) {
            if (animal.getGenome().equals(genome)) {
                animalsWithGenome.add(animal);
            }
        }
        return animalsWithGenome;
    }

    public void stats() {
        System.out.println(aliveAnimals.size());
        System.out.println(map.getPlantCount());
        System.out.println(map.calculateEmptyTiles());
        System.out.println(this.findMostPopularGenome());
        System.out.println(this.calcAvgEnergy());
        System.out.println(this.calcAvgLifespan());
        System.out.println(this.calcAvgChildren());
    }


    public void visualStats() {
        this.getAnimalsWithGivenGenome(findMostPopularGenome());  //stay aware of complexity
        planter.getPreferredTiles();
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

        // Update genome frequency
        updateGenomeFrequency(deadThisStep, newThisStep);
    }

}

