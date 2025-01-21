package agh.ics.oop.presenters;

import agh.ics.oop.organisms.Animal;
import agh.ics.oop.organisms.Plant;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class DisplayCell extends StackPane {


    public DisplayCell(Animal animal, Plant plant,double size){
        super();

        if (plant != null){
            Rectangle rectangle = new Rectangle(size,size);
            rectangle.setFill(Color.valueOf("#125D18"));
            getChildren().add(rectangle);
            StackPane.setAlignment(rectangle, Pos.CENTER);
        }

        if (animal != null){
            Circle circle = new Circle(Math.min(size/2,animal.getAge()/20f*size));
            Color color = Color.BLACK.interpolate(Color.YELLOW,Math.min(1,animal.getEnergy()/50f));
            circle.setFill(color);
            getChildren().add(circle);
            StackPane.setAlignment(circle, Pos.CENTER);
        }


    }



}
