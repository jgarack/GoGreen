package gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;

import java.util.ResourceBundle;

public class MainController {

    private int vegetarianMeals = 0;
    private int bicycleUsed = 0;

    @FXML
    protected TextField VegMeals;
    @FXML
    protected Label Veg_Meals_eaten;

    @FXML
    protected TextField BicycleUsage;
    @FXML
    protected Label Bicycle_Used;

    @FXML
    protected void decreaseVegetarianMeals(ActionEvent event){
        this.vegetarianMeals -= Integer.parseInt(VegMeals.getText());
        Veg_Meals_eaten.setText("Vegetarian meals eaten:" + this.vegetarianMeals);

    }

    @FXML
    protected void increaseVegetarianMeals(ActionEvent event){
        this.vegetarianMeals += Integer.parseInt(VegMeals.getText());
        Veg_Meals_eaten.setText("Vegetarian meals eaten:" + this.vegetarianMeals);
    }

    @FXML
    protected void decreaseBicycleUsage(ActionEvent event){
        this.bicycleUsed += Integer.parseInt(BicycleUsage.getText());
        Bicycle_Used.setText("I have used a bicycle today:"+ this.bicycleUsed);
    }

    @FXML
    protected void increaseBicycleUsage(ActionEvent event){
        this.bicycleUsed += Integer.parseInt(BicycleUsage.getText());
        Bicycle_Used.setText("I have used a bicycle today:"+ this.bicycleUsed);
    }

}
