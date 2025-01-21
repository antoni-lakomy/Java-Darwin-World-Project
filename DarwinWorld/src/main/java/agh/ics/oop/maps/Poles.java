package agh.ics.oop.maps;

import agh.ics.oop.model.GeneInterpreter;
import agh.ics.oop.organisms.Animal;
import agh.ics.oop.planters.ForestedEquators;
import agh.ics.oop.records.Vector2d;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Poles extends AbstractWorldMap {
    private int maxMoveCost = 10;

    public Poles(int width, int height, GeneInterpreter geneInterpreter) {
        super(width, height, geneInterpreter);
    }

    public void moveAnimal(Animal animal) {
        int energyRequired = calculateEnergy(animal.getPosition().y());
        Vector2d moveVector = animal.activateGene(geneInterpreter, energyRequired);
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

    // Closer it gets to Pole, higher cost of move gets, except equators, where energy required is always 1.
    // Since an animal stepped out of the equator - its move energy rises gradually starting at 2, up to maxMoveCost.
    public int calculateEnergy(int positionY) {
        int maxValidDistance = (int) (0.4f * this.height);
        int distance = min(positionY, this.height - positionY - 1);
        if (distance >= maxValidDistance) {return 1;}
        int demandedEnergy = max(2, (int)((float)maxMoveCost - ((float)distance / maxValidDistance * maxMoveCost)));

        return demandedEnergy;
    }

}