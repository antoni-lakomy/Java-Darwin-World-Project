package agh.ics.oop;


import agh.ics.oop.maps.Globe;
import agh.ics.oop.maps.WorldMap;
import agh.ics.oop.model.FullPredestination;
import agh.ics.oop.organisms.Animal;
import agh.ics.oop.organisms.AnimalBuilder;
import agh.ics.oop.planters.ForestedEquators;
import agh.ics.oop.planters.Planter;
import agh.ics.oop.records.SimParams;
import agh.ics.oop.records.Vector2d;
import agh.ics.oop.util.MapVisualizer;

import java.util.ArrayList;
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

        WorldMap worldMap = new Globe(5,5, new FullPredestination());
        //worldMap.addAnimal(a);
        //worldMap.addAnimal(b);
        //worldMap.addAnimal(c);

        Planter planter = new ForestedEquators(worldMap,20,11);

        planter.plant(9);

        planter.plant(9);

        MapVisualizer visualizer = new MapVisualizer(worldMap);

        for (int i = 0; i < 1; i++){
            System.out.println(visualizer.draw(new Vector2d(0,0),new Vector2d(4,4)));

            //worldMap.moveAnimal(animals.get(i % 3));
        }





    }
}
