package agh.ics.oop.maps;

import agh.ics.oop.model.GeneInterpreter;
import agh.ics.oop.organisms.Animal;
import agh.ics.oop.records.Vector2d;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Poles extends AbstractWorldMap {

    public Poles(int width, int height, GeneInterpreter geneInterpreter) {
        super(width, height, geneInterpreter);
    }

    public void moveAnimal(Animal animal) {
        int energyRequired = calculateEnergy(animal.getPosition().y());
        Vector2d moveVector = animal.activateGene(geneInterpreter, energyRequired);
        Vector2d oldPosition = animal.getPosition();
        Vector2d newPosition = animal.getPosition().add(moveVector);
        newPosition = boundPosition(oldPosition, newPosition, animal);

        if (!map[oldPosition.x()][oldPosition.y()].removeAnimal(animal))
            throw new NullPointerException("Animal was not present on the map");

        map[newPosition.x()][newPosition.y()].addAnimal(animal);
        animal.setPosition(newPosition);
    }

    public int calculateEnergy(int positionY) {
        int mapHeight = getHeight();
        int distance = min(positionY, mapHeight - positionY);
        int demandedEnergy = max(1, 10 - ((distance * 2 / mapHeight) * 10)); // closer it gets to Pole, higher cost gets. Energy rises gradually starting at 1, up to 10.
        return demandedEnergy;
    }

}