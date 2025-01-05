package agh.ics.oop;


import agh.ics.oop.organisms.Animal;
import agh.ics.oop.organisms.AnimalBuilder;
import agh.ics.oop.records.SimParams;
import agh.ics.oop.records.Vector2d;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world");

        SimParams params = new SimParams(123,0,0,
                                0,0,0,
                            0,0,0,
                        50,0,20,
                    6, 0,0,0,0);

        AnimalBuilder builder = new AnimalBuilder(params);

        Byte[] geneA = {1,1,1,1,1,1};
        Byte[] geneB = {2,2,2,2,2,2};

        Animal a = builder.buildBase(new Vector2d(1,1), 200,geneA);
        Animal b = builder.buildBase(new Vector2d(1,1),50,geneB);

        Animal c = builder.buildFromParents(a,b);

        System.out.println(c.getParents());
        System.out.println(Arrays.toString(c.getGenome()));
        System.out.println(a.getChildren());

    }
}
