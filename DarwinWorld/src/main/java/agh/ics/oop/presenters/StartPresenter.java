package agh.ics.oop.presenters;

import agh.ics.oop.observers.ConsoleDisplay;
import agh.ics.oop.records.SimParams;
import agh.ics.oop.simulation.Simulation;
import agh.ics.oop.simulation.SimulationBuilder;
import agh.ics.oop.simulation.SimulationDriver;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.security.InvalidParameterException;


public class StartPresenter {

    private Application app;

    @FXML
    private TextField seedField;


    @FXML
    private TextField mapHeightField;

    @FXML
    private TextField mapWidthField;

    @FXML
    private ChoiceBox<String>  mapTypeField;


    @FXML
    private TextField plantStartNumberField;

    @FXML
    private TextField plantEnergyField;

    @FXML
    private TextField plantGrowthField;


    @FXML
    private TextField animalStartNumberField;

    @FXML
    private TextField animalStartEnergyField;

    @FXML
    private TextField animalFedThresholdField;

    @FXML
    private TextField animalReproductionCostField;

    @FXML
    private TextField animalGenomeLengthField;

    @FXML
    private ChoiceBox<String>  animalBehaviourTypeField;

    @FXML
    private TextField animalMinMutationField;

    @FXML
    private TextField animalMaxMutationField;

    long seed;

    int mapHeight;
    int mapWidth;
    int mapType;

    int plantStartNumber;
    int plantEnergy;
    int plantGrowth;

    int animalStartNumber;
    int animalStartEnergy;
    int animalFedThreshold;
    int animalReproductionCost;
    int animalGenomeLength;
    int animalBehaviourType;
    int animalMinMutation;
    int animalMaxMutation;

    public void setApp(Application app) {
        this.app = app;
    }


    private Long verifyLong(TextField textField, String name){
        if (textField.getText().isEmpty()) throw new IllegalArgumentException(name + " is empty.");
        try{
            return Long.parseLong(textField.getText());
        } catch (Exception e){
            throw new IllegalArgumentException(name + " is not numeric");
        }
    }

    private int verifyInt(TextField textField, String name, int minValue){
        int result;
        if (textField.getText().isEmpty()) throw new IllegalArgumentException(name + " is empty.");
        try{
            result = Integer.parseInt(textField.getText());
        } catch (Exception e){
            throw new IllegalArgumentException(name + " is not numeric");
        }
        if (result < minValue) throw new IllegalArgumentException(name + "is less then the min value of " + minValue);
        return result;
    }



    private void verifyData(){
        seed = verifyLong(seedField,"Seed");

        mapHeight = verifyInt(mapHeightField,"Map Height",1);
        mapWidth = verifyInt(mapWidthField,"Map Width",1);

        plantStartNumber = verifyInt(plantStartNumberField,"Plant Start Number",0);
        plantEnergy = verifyInt(plantEnergyField,"Plant Energy",0);
        plantGrowth = verifyInt(plantGrowthField,"Plant Growth",0);

        animalStartNumber = verifyInt(animalStartNumberField,"Animal Start Number",0);
        animalStartEnergy = verifyInt(animalStartEnergyField,"Animal Start Energy",0);
        animalFedThreshold = verifyInt(animalFedThresholdField,"Animal Fed Threshold",0);
        animalReproductionCost = verifyInt(animalReproductionCostField,"Animal Reproduction Cost",0);
        animalGenomeLength = verifyInt(animalGenomeLengthField,"Animal Genome Length",1);
        animalMinMutation = verifyInt(animalMinMutationField,"Animal Min Mutation",0);
        animalMaxMutation = verifyInt(animalMaxMutationField,"Animal Max Mutation",0);

        if (animalFedThreshold < animalReproductionCost)
            throw new IllegalArgumentException("Fed Threshold can't be lower than Reproduction Cost");

        if (animalMinMutation > animalMaxMutation)
            throw new IllegalArgumentException("Min Mutation can't be higher then Max Mutation");

        mapType = mapTypeField.getItems().indexOf(mapTypeField.getValue());
        animalBehaviourType = animalBehaviourTypeField.getItems().indexOf(animalBehaviourTypeField.getValue());
    }

    private SimParams createSimParams(){
        return new SimParams(
                seed,
                mapHeight,
                mapWidth,
                mapType,
                plantStartNumber,
                plantEnergy,
                plantGrowth,
                0,
                animalStartNumber,
                animalStartEnergy,
                animalFedThreshold,
                animalReproductionCost,
                animalGenomeLength,
                animalBehaviourType,
                animalMinMutation,
                animalMaxMutation,
                0
                );
    }

    public void onSimulationStartClicked(){
        try {
            verifyData();
            SimParams parameters = createSimParams();
            Simulation simulation = SimulationBuilder.build(parameters);
            ConsoleDisplay display = new ConsoleDisplay();
            simulation.addObserver(display);
            SimulationDriver driver = new SimulationDriver(simulation);
            Thread thread = new Thread(driver);
            thread.start();
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }



}
