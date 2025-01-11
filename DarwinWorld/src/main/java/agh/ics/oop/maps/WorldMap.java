package agh.ics.oop.maps;

import agh.ics.oop.organisms.Animal;
import agh.ics.oop.organisms.Organism;
import agh.ics.oop.records.Vector2d;

public interface WorldMap {


    /**
     * Checks if the tile on the given position is empty.
     *
     * @param position The position to check.
     * @return true if the tile is empty, false otherwise.
     */
    boolean isFieldEmpty(Vector2d position);

    /**
     * Gets the organism from the tile at a given position.
     * Will throw an exception if the tile is not sorted beforehand;
     *
     * @param position The position to check.
     * @return The organism at the given position>
     *
     * @throws IllegalStateException if the tile is not sorted beforehand.
     */
    Organism getOrganismAt(Vector2d position);

    /**
     * Adds an animal to the map.
     * @param animal {@link Animal} to be added.
     */
    void addAnimal(Animal animal);

    /**
     * Removes an animal from the map.
     * @param animal {@link Animal} to be removed.
     */
    void removeAnimal(Animal animal);


    /**
     * Binds the provided position to the map borders including
     * special map features.
     *
     * @param position The position to bind.
     * @return A {@link Vector2d} containing the bound position.
     */
    Vector2d boundPosition(Vector2d position);


    /**
     * Moves an animal on the map according to its genome
     * and this maps specifications.
     *
     * @param animal {@link Animal} to move.
     *
     * @throws NullPointerException if the animal was not present on the map.
     */
    void moveAnimal(Animal animal);
}
