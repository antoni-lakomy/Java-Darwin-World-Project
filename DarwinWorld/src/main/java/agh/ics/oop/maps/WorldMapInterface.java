package agh.ics.oop.maps;

import agh.ics.oop.organisms.Organism;
import agh.ics.oop.records.Vector2d;

import java.util.List;

public interface WorldMapInterface {


    boolean isFieldEmpty(Vector2d position);
    // Checks if specified field is empty

    List<Organism> getOrganismsAt(Vector2d position);
    // Returns list of organisms standing at specified field

    void addOrganism(Organism organism);
    // Adds new organism to the map

    void removeOrganism(Organism organism);
    // Removes organism from the map
}
