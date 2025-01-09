package agh.ics.oop.model;

import agh.ics.oop.maps.WorldMapInterface;
import agh.ics.oop.organisms.Animal;
import agh.ics.oop.organisms.AnimalBuilder;
import agh.ics.oop.organisms.Organism;
import agh.ics.oop.organisms.Plant;

import java.util.ArrayList;
import java.util.List;

/**
 * A single tile on the {@link WorldMapInterface}.
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
     * @return true - if the tile does not contain any plant or animal,
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
     * @return The first organism on this tile.
     *
     * @throws IllegalStateException if the tile is not sorted beforehand.
     */
    public Organism getOrganism() {
        if (!sorted) throw new IllegalStateException("Trying to get an organism from an unsorted tile");
        if (animals.isEmpty()) return plant;
        return animals.getFirst();
    }

    /**
     * Adds an animal to the tile.
     * Breaks sorting.
     *
     * @param animal Animal to be added to the map.
     */
    public void addAnimal(Animal animal) {
        animals.add(animal);
        sorted = false;
    }

    /**
     * Removes an animal from the map.
     * Retains sorting.
     *
     * @param animal Animal to be added to the map.
     *
     * @return true - if the tile contained the provided animal.
     */
    public boolean removeAnimal(Animal animal){
        return animals.remove(animal);
    }

    /**
     * Sorts the animals on the tile using {@link AnimalComparator}.
     * That way animals are placed from the strongest to the weakest ones.
     * It is necessary to do this before initiating the consumption
     * and multiplication faze of the simulation to preserve the
     * correct order.
     */
    private void sortAnimals(){
        if (sorted) return;
        animals.sort(AnimalComparator.getInstance());
        sorted = true;
    }

    /**
     * If both animals and a plant are present on the tile, then
     * makes the strongest animal eat the plant, destroying it in the process.
     *
     * @throws IllegalStateException if the tile is not sorted prior to the consumption.
     */
    public void tryToConsumePlant(){
        if (!sorted) throw new IllegalStateException("Trying to consume plants on an unsorted tile");
        if (animals.isEmpty()) return;
        if (plant != null){
            animals.getFirst().addEnergy(plant.getEnergy());
            plant = null;
        }
    }

    /**
     * If there are two or more animals on the tile, and they are fed enough to multiply,
     * then uses the provided {@link AnimalBuilder} to make a new child and add it to the tile.
     * Sorts the tile afterward to preserve the correct order.
     *
     * @param builder the builder used to create children.
     * @param fedThreshold the minimal energy animal has to have to be considered
     *                     fed and ready to reproduce.
     *
     * @throws IllegalStateException if the tile is not sorted beforehand.
     */
    public void tryToMultiply(AnimalBuilder builder, int fedThreshold){
        if (!sorted) throw new IllegalStateException("Trying to multiply on an unsorted tile");
        for (int i = 1; i < animals.size(); i += 2){

            if (animals.get(i).getEnergy() < fedThreshold) break;
            sorted = false;
            animals.add(builder.buildFromParents(animals.get(i-1),animals.get(i)));
        }
        sortAnimals();
    }


}
