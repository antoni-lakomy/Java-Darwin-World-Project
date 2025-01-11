package agh.ics.oop.planters;

import agh.ics.oop.maps.WorldMap;

public interface Planter {


    /**
     * Plant a given number of plants on this planters {@link WorldMap}.
     *
     * @param numberToPlant A number of plants to plant.
     */
    void plant(int numberToPlant);
}
