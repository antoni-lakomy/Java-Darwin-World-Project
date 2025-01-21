package agh.ics.oop.presenters;

import agh.ics.oop.maps.WorldMap;
import agh.ics.oop.observers.SimObserver;
import agh.ics.oop.records.Vector2d;
import agh.ics.oop.simulation.Simulation;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Arrays;


public class SimulationPresenter implements SimObserver {

    private final static double maxGridSize = 600;

    private double gridCellSize;


    @FXML
    GridPane mapGrid;

    @FXML Label simulationDay;
    @FXML Label aliveAnimals;
    @FXML Label deadAnimals;
    @FXML Label avgEnergy;
    @FXML Label avgLifespan;
    @FXML Label avgChildren;
    @FXML Label mostPopularGenome;
    @FXML Label plantCount;
    @FXML Label emptyTiles;



    Simulation simulation;

    @FXML
    public void closeWindowEvent(WindowEvent event) {
        simulation.shutDown();
    }

    public void setSimulation(Simulation simulation){
        this.simulation = simulation;

        //region GridPane setup
        WorldMap map = simulation.getMap();
        mapGrid.setPadding(new Insets(0));
        double cellWidth = maxGridSize / (double)(map.getWidth());
        double cellHeight = maxGridSize / (double)(map.getHeight());
        gridCellSize = Math.min(cellWidth,cellHeight);

        mapGrid.setPrefSize(gridCellSize * map.getWidth(),gridCellSize * map.getHeight());
        mapGrid.setMaxSize(gridCellSize * map.getWidth(),gridCellSize * map.getHeight());


        for (int x = 0; x < map.getWidth(); x++){
            mapGrid.getColumnConstraints().add(new ColumnConstraints(gridCellSize));
        }

        for (int y = 0; y < map.getHeight(); y++){
            mapGrid.getRowConstraints().add(new RowConstraints(gridCellSize));
        }
        //endregion

    }

    @Override
    public void update(Simulation simulation) {
        Platform.runLater(() -> {
            updateStatistics(simulation);
            drawMap(simulation);
        });
    }

    public synchronized void pauseUnpauseSimulation(){
        simulation.changePause();
    }

    public synchronized void updateStatistics(Simulation simulation){
        simulationDay.setText("Day: " + simulation.getSimulationDay());
        aliveAnimals.setText("Alive Animals: " + simulation.getAliveAnimalsSize());
        deadAnimals.setText("Dead Animals: " + simulation.getDeadAnimalsSize());
        avgEnergy.setText("Avg Energy: " + simulation.getAvgEnergy());
        avgLifespan.setText("Avg Lifespan: " + simulation.getAvgLifespan());
        avgChildren.setText("Avg Children: " + simulation.getAvgChildren());
        mostPopularGenome.setText("Most Popular Genome:\n" + Arrays.toString(this.simulation.getMostPopularGenome()));
        plantCount.setText("Plant Count: " + simulation.getPlantCount());
        emptyTiles.setText("Empty Tiles: " + simulation.getEmptyTiles());
    }

    public synchronized void drawMap(Simulation simulation){
        mapGrid.getChildren().retainAll(mapGrid.getChildren().getFirst());
        WorldMap map = simulation.getMap();

        //region elements
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Vector2d poz = new Vector2d(x, y);
                if (!map.isFieldEmpty(poz)){
                    DisplayCell cell = new DisplayCell(map.getAnimalAt(poz),map.getPlantAt(poz),gridCellSize);

                    mapGrid.add(cell, x, map.getHeight() - (1 + y));
                    GridPane.setHalignment(cell, HPos.CENTER);
                }

            }
        }
        //endregion
    }

}
