package agh.ics.oop.observers;

import agh.ics.oop.maps.WorldMap;

public interface SimObserver {

    /**
     * Invoked when a simulation this object observes
     * has finished its step and is ready to be displayed.
     *
     * @param map The {@link WorldMap to display}
     */
    void update(WorldMap map);
}
