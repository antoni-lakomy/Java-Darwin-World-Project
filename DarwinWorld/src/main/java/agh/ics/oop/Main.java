package agh.ics.oop;


import agh.ics.oop.observers.ConsoleDisplay;
import agh.ics.oop.observers.SimObserver;
import agh.ics.oop.records.SimParams;
import agh.ics.oop.simulation.Simulation;
import agh.ics.oop.simulation.SimulationBuilder;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world");

        SimParams params = new SimParams(11,10,10,
                                1,10,30,
                            3,0,5,
                        100,20,10,
                    10, 1,0,4,0);


        Simulation simulation = SimulationBuilder.build(params);

        SimObserver display = new ConsoleDisplay();

        simulation.addObserver(display);


        for (int i = 0; i < 1000; i++){
            simulation.simulationStep();
        }


    }
}
