package agh.ics.oop.simulation;

//TODO W. I. P. - jeszcze nie wiem, czy tego nie zrobić w samej symulacji
public class SimulationDriver implements Runnable{

    Simulation simulation;

    public SimulationDriver(Simulation simulation){
        this.simulation = simulation;
    }


    @Override
    public void run() {
        for (int i = 0; i < 1000;i++){
            simulation.simulationStep();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
