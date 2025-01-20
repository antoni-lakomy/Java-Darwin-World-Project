package agh.ics.oop.presenters;

import agh.ics.oop.maps.WorldMap;
import agh.ics.oop.observers.SimObserver;
import agh.ics.oop.records.Vector2d;
import agh.ics.oop.simulation.Simulation;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class SimulationPresenter implements SimObserver {

    private final static int maxWidth = 400;
    private final static int maxHeight = 400;

    @FXML
    GridPane mapGrid;

    @FXML Label aliveAnimals;
    @FXML Label deadAnimals;



    @Override
    public void update(Simulation simulation) {
        Platform.runLater(() -> {
            updateStatistics(simulation);
            drawMap(simulation);
        });
    }

    public void updateStatistics(Simulation simulation){
        aliveAnimals.setText("Alive Animals: " + simulation.getAliveAnimalsSize());
        deadAnimals.setText("Dead Animals: " + simulation.deadAnimals.size());
    }

    public void drawMap(Simulation simulation){
        clearGrid();
        WorldMap map = simulation.getMap();

        //region cellSize
        double cellWidth = maxWidth / (double)(map.getWidth());
        double cellHeight = maxHeight / (double)(map.getHeight());
        cellHeight = Math.min(cellWidth,cellHeight); //Na razie są jednakowe,
        cellWidth = Math.min(cellWidth,cellHeight);  //ale mogą być różne
        //endregion


        for (int x = 0; x < map.getWidth(); x++){
            mapGrid.getColumnConstraints().add(new ColumnConstraints(cellWidth));
        }

        for (int y = 0; y < map.getHeight(); y++){
            mapGrid.getRowConstraints().add(new RowConstraints(cellHeight));
        }


        //region elements
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Vector2d poz = new Vector2d(x, y);
                Label label = new Label("");
                if (!map.isFieldEmpty(poz)){
                    label = new Label(map.getOrganismAt(poz).toString());
                }
                mapGrid.add(label, x, map.getHeight() - (1 + y));
                GridPane.setHalignment(label, HPos.CENTER);
            }
        }
        //endregion
    }

    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0)); // hack to retain visible grid lines
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }

}
