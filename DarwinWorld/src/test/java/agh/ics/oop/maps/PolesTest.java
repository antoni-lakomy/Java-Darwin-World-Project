package agh.ics.oop.maps;

import agh.ics.oop.enums.MapDirection;
import agh.ics.oop.model.FullPredestination;
import agh.ics.oop.model.GeneInterpreter;
import agh.ics.oop.organisms.Animal;
import agh.ics.oop.organisms.AnimalBuilder;
import agh.ics.oop.records.Vector2d;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolesTest {

    GeneInterpreter geneInterpreter = new FullPredestination();

    @Test
    void closerAnimalGetsToNorthPole_MoreEnergyItConsumesMoving() {
        // Given animals with the same starting energy, but one closer to the pole than the other
        AnimalBuilder builder = new AnimalBuilder();
        Byte[] genome = new Byte[]{0, 2, 4, 2, 6, 4, 3, 7, 2, 3};

        Animal animal1 = builder.buildBase(new Vector2d(30, 81), 100, genome);
        Animal animal2 = builder.buildBase(new Vector2d(35, 62), 100, genome);

        animal1.setOrientation(MapDirection.NORTH);
        animal2.setOrientation(MapDirection.NORTH);

        animal1.setCurrentGene(0);
        animal2.setCurrentGene(0);

        AbstractWorldMap map = new Poles(100, 100, geneInterpreter);

        map.addAnimal(animal1);
        map.addAnimal(animal2);

        // When
        map.moveAnimal(animal1);
        map.moveAnimal(animal2);

        System.out.println(animal1.getEnergy());
        System.out.println(animal2.getEnergy());
        // Then
        assertTrue(animal1.getEnergy() < animal2.getEnergy(), "Expected for animal1 to consume more energy because its closer to pole");

    }
}