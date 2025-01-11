package agh.ics.oop.model;

import agh.ics.oop.maps.WorldMap;
import agh.ics.oop.organisms.Animal;
import agh.ics.oop.organisms.AnimalBuilder;
import agh.ics.oop.organisms.Organism;
import agh.ics.oop.organisms.Plant;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * A single tile on the {@link WorldMap}.
 * Contains a spot for a single {@link Plant} and a list of {@link Animal} objects.
 * Provides methods for basic {@link Animal} behaviour on the tile
 * like eating the plant or multiplying.
 */
public class WorldTile {

    private Plant plant = null;

    private boolean sorted = true;

    private final List<Animal> animals = new ArrayList<>();

    /**
     * Checks if the tile is empty.
     *
     * @return true - if the tile does not contain any {@link Plant} or {@link Animal},
     *         otherwise false.
     */
    public boolean isEmpty(){
        return animals.isEmpty() && plant == null;
    }

    /**
     * Returns an organism on the tile.
     * Animals have priority, and if the tile is already sorted
     * returns the strongest animal on the tile.
     * If there are no animals returns the plant if present, otherwise null.
     *
     * @return The first {@link Organism} on this tile.
     */
    public Organism getOrganism() {
        sortAnimals();
        if (animals.isEmpty()) return plant;
        return animals.getFirst();
    }

    /**
     * Adds an animal to the tile.
     *
     * @param animal {@link Animal} to be added to the map.
     */
    public void addAnimal(Animal animal) {
        animals.add(animal);
        sorted = false;
    }

    /**
     * Removes an animal from the map.
     * Retains sorting.
     *
     * @param animal {@link Animal} to be added to the map.
     *
     * @return true - if the tile contained the provided animal.
     */
    public boolean removeAnimal(Animal animal){
        return animals.remove(animal);
    }

    /**
     * Adds a plant to the tile.
     * The tile must not have a plant already.
     *
     * @param plant {@link Plant} to be added to the map.
     *
     * @throws IllegalStateException if there already is a plant on the tile
     */
    public void addPlant(Plant plant){
        if (this.plant != null)
            throw new IllegalStateException("Trying to add a plant to an already occupied tile");
        this.plant = plant;
    }

    /**
     * Removes the plant on the tile if not already empty.
     *
     * @return true - if there was a plant to remove, otherwise false
     */
    public boolean removePlant(){
        if (this.plant == null) return false;
        this.plant = null;
        return true;
    }

    /**
     * Sorts the animals on the tile using {@link AnimalComparator}.
     * That way animals are placed from the strongest to the weakest ones.
     * It is necessary to do this before initiating the consumption
     * and multiplication fazes of the simulation to preserve the
     * correct order.
     */
    private void sortAnimals(){
        if (sorted) return;
        animals.sort(AnimalComparator.getInstance());
        sorted = true;
    }

    /**
     * Removes the dead animals from this tile.
     *
     * @return A list of the remove animals.
     */
    public List<Animal> removeDeadAnimals(){
        sortAnimals();
        List<Animal> deadAnimals = new ArrayList<>();
        for (int i = animals.size()-1; i >= 0; i--){
            if (animals.get(i).getEnergy() > 0) break;
            deadAnimals.add(animals.get(i));
            animals.remove(i);
        }
        return deadAnimals;
    }



    /**
     * If both animals and a plant are present on the tile, then
     * makes the strongest animal eat the plant, destroying it in the process.
     *
     * @return true - if the plant was successfully eaten, false otherwise.
     */
    public boolean tryToConsumePlant(){
        sortAnimals();
        if (animals.isEmpty()) return false;
        if (plant != null){
            animals.getFirst().addEnergy(plant.getEnergy());
            plant = null;
            return true;
        }
        return false;
    }

    /**
     * If there are two or more animals on the tile, and they are fed enough to multiply,
     * then uses the provided {@link AnimalBuilder} to make a new child and add it to the tile.
     * Returns the newly created children.
     *
     * @param builder the builder used to create children.
     * @param fedThreshold the minimal energy animal has to have to be considered
     *                     fed and ready to reproduce.
     *
     * @return A list of new animals created on this tile.
     */
    public List<Animal> tryToMultiply(AnimalBuilder builder, int fedThreshold){
        sortAnimals();
        List<Animal> newAnimals = new LinkedList<>();
        for (int i = 1; i < animals.size(); i += 2){

            if (animals.get(i).getEnergy() < fedThreshold) break;
            sorted = false;
            Animal newAnimal = builder.buildFromParents(animals.get(i-1),animals.get(i));
            animals.add(newAnimal);
            newAnimals.addFirst(newAnimal);
        }
        return newAnimals;
    }


}
