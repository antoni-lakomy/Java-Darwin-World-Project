package agh.ics.oop.maps;

import agh.ics.oop.organisms.AbstractOrganism;
import agh.ics.oop.records.Vector2d;

import java.util.*;

public class WorldMap implements WorldMapInterface {
    private final int width;
    private final int height;
    private final Map<Vector2d, List<AbstractOrganism>> map;

    public WorldMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new HashMap<>();
        initializeMap();
    }

    // Assigns empty list for each field on the map. Each list will then contain organisms standing on that field.
    // Doing so, we do not need to check each time if we got particular key in the map HashMap, we can just simply
    // refer to it when we want to get information about organisms occupying that field or when we want to take any action with organism (e.g. add or remove)
    private void initializeMap() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                map.put(new Vector2d(x, y), new ArrayList<>());
            }
        }
    }

    @Override
    public boolean isFieldEmpty(Vector2d position) {
        return map.get(position).isEmpty();
    }

    @Override
    public List<AbstractOrganism> getOrganismsAt(Vector2d position) {
        return map.get(position);
    }

    @Override
    public void addOrganism(AbstractOrganism organism) {
        map.get(organism.getPosition()).add(organism);
    }

    @Override
    public void removeOrganism(AbstractOrganism organism) {
        map.get(organism.getPosition()).remove(organism);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void moveOrganism(AbstractOrganism organism, Vector2d newPosition) {
        List<AbstractOrganism> currentField = map.get(organism.getPosition());
        List<AbstractOrganism> newField = map.get(newPosition);

        currentField.remove(organism);
        organism.setPosition(newPosition);
        newField.add(organism);
    }
}

