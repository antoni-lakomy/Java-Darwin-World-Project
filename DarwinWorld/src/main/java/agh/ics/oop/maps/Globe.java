package agh.ics.oop.maps;

import agh.ics.oop.model.GeneInterpreter;
import agh.ics.oop.organisms.Animal;
import agh.ics.oop.records.Vector2d;

public class Globe extends AbstractWorldMap {

    public Globe(int width, int height, GeneInterpreter geneInterpreter) {
        super(width, height, geneInterpreter);
    }

    public void moveAnimal(Animal animal) {
        Vector2d moveVector = animal.activateGene(geneInterpreter,1);
        Vector2d oldPosition = animal.getPosition();
        Vector2d newPosition;
        if (!animal.isSkippingMove()) {
            newPosition = animal.getPosition().add(moveVector);
            newPosition = boundPosition(newPosition, animal);
        } else {
            newPosition = oldPosition;
            animal.setSkippingMove(false);
        }

        if (!map[oldPosition.x()][oldPosition.y()].removeAnimal(animal))
            throw new NullPointerException("Animal was not present on the map");

        map[newPosition.x()][newPosition.y()].addAnimal(animal);
        animal.setPosition(newPosition);
    }

}

