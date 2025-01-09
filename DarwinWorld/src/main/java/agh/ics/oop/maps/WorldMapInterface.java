package agh.ics.oop.maps;

import agh.ics.oop.organisms.Animal;
import agh.ics.oop.organisms.Organism;
import agh.ics.oop.organisms.Plant;
import agh.ics.oop.records.Vector2d;

import java.util.List;

public interface WorldMapInterface {


    boolean isFieldEmpty(Vector2d position);
    // Checks if specified field is empty


    //TODO - eleganckie opisy funkcji
    Organism getOrganismAt(Vector2d position);

    void addAnimal(Animal animal);

    void removeAnimal(Animal organism);

    void moveAnimal(Animal animal);
}
