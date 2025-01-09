package agh.ics.oop.maps;

import agh.ics.oop.model.FullPredestination;
import agh.ics.oop.model.GeneInterpreter;
import agh.ics.oop.model.WorldTile;
import agh.ics.oop.organisms.Animal;
import agh.ics.oop.organisms.Organism;
import agh.ics.oop.records.Vector2d;

import java.util.*;

public class WorldMap implements WorldMapInterface {
    private final int width;
    private final int height;
    private final WorldTile[][] map;
    private final GeneInterpreter geneInterpreter = new FullPredestination();

    public WorldMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new WorldTile[width][height];
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                map[x][y] = new WorldTile();
            }
        }
    }

    @Override
    public boolean isFieldEmpty(Vector2d position) {
        return map[position.x()][position.y()].isEmpty();
    }

    @Override
    public Organism getOrganismAt(Vector2d position) {
        return map[position.x()][position.y()].getOrganism();
    }

    //TODO - może obsługa postawienia tego samego zwierzaka wiele razy
    @Override
    public void addAnimal(Animal animal) {
        Vector2d poz = animal.getPosition();
        map[poz.x()][poz.y()].addAnimal(animal);
    }

    @Override
    public void removeAnimal(Animal animal) {
        Vector2d poz = animal.getPosition();
        map[poz.x()][poz.y()].removeAnimal(animal);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void moveAnimal(Animal animal) {
        Vector2d moveVector = animal.activateGene(geneInterpreter);
        Vector2d oldPosition = animal.getPosition();
        Vector2d newPosition = animal.getPosition().add(moveVector);
        if (newPosition.x() >= width) newPosition = new Vector2d(0,newPosition.y());
        if (newPosition.x() < 0) newPosition = new Vector2d(width-1, newPosition.y());
        if (newPosition.y() >= width) newPosition = new Vector2d(newPosition.x(),height-1);
        if (newPosition.y() < 0) newPosition = new Vector2d(newPosition.x(),0);

        if (!map[oldPosition.x()][oldPosition.y()].removeAnimal(animal))
            throw new NullPointerException("Animal was not present on the map");

        map[newPosition.x()][newPosition.y()].addAnimal(animal);
        animal.setPosition(newPosition);
    }
}

