package agh.ics.oop;


import agh.ics.oop.maps.WorldMap;
import agh.ics.oop.maps.WorldMapInterface;
import agh.ics.oop.model.AnimalComparator;
import agh.ics.oop.organisms.Animal;
import agh.ics.oop.organisms.AnimalBuilder;
import agh.ics.oop.records.SimParams;
import agh.ics.oop.records.Vector2d;
import agh.ics.oop.util.MapVisualizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world");

        SimParams params = new SimParams(11,0,0,
                                0,0,0,
                            0,0,0,
                        50,0,20,
                    6, 0,0,0,0);

        AnimalBuilder builder = new AnimalBuilder(params);

        Byte[] geneA = {1,1,1,1,1,1};
        Byte[] geneB = {2,2,2,2,2,2};

        Animal a = builder.buildBase(new Vector2d(1,1), 200,geneA);
        Animal b = builder.buildBase(new Vector2d(1,1),60,geneB);

        Animal c = builder.buildFromParents(a,b);

        List<Animal> animals = new ArrayList<>();
        animals.add(a);
        animals.add(b);
        animals.add(c);

        WorldMap worldMap = new WorldMap(5,5);
        worldMap.addAnimal(a);
        worldMap.addAnimal(b);
        worldMap.addAnimal(c);

        MapVisualizer visualizer = new MapVisualizer(worldMap);

        for (int i = 0; i < 20; i++){
            System.out.println(visualizer.draw(new Vector2d(0,0),new Vector2d(4,4)));
            
            worldMap.moveAnimal(animals.get(i % 3));
        }





    }
}
