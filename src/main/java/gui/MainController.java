package gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;

import java.util.ResourceBundle;

/**
 * Controller for the main window.
 */
public class MainController {

    /**
     * Data about vegetarian meals consumed that is to be retrieved from database.
     */
    private int vegetarianMeals = 0;
    /**
     * Data about the times that a bicycle has been used that is to be retrieved from database.
     */
    private int bicycleUsed = 0;

    /**
     * Bound to the text field linked with vegetarian meals.
     */
    @FXML
    protected TextField VegMeals;
    /**
     * Bound to the label about the vegetarian meals - used to show progress.
     */
    @FXML
    protected Label Veg_Meals_eaten;

    /**
     * Bound to the text field about the usage of bicycle.
     */
    @FXML
    protected TextField BicycleUsage;
    /**
     * Bound to the bicycle label - used to show progress.
     */
    @FXML
    protected Label Bicycle_Used;

    /**
     * Used to decrease the amount of vegetarian meals filled in the text field.
     * @param event The fired event when the decrease button is pressed.
     */
    @FXML
    protected void decreaseVegetarianMeals(ActionEvent event){
            if(tryParseInt(VegMeals.getText())) {
                this.vegetarianMeals -= Integer.parseInt(VegMeals.getText());
                Veg_Meals_eaten.setText("Vegetarian meals eaten:" + this.vegetarianMeals);
            }
            else{
                generateAlert("You need to fill in a number!");
            }
    }

    /**
     * Used to increase the amount of vegetarian meals filled in the text field.
     * @param event The fired event when the increase button is pressed.
     */
    @FXML
    protected void increaseVegetarianMeals(ActionEvent event) {
        if (tryParseInt(VegMeals.getText())) {
            this.vegetarianMeals += Integer.parseInt(VegMeals.getText());
            Veg_Meals_eaten.setText("Vegetarian meals eaten:" + this.vegetarianMeals);
        } else {
            generateAlert("You need to fill in a number!");
        }
    }

    /**
     * Used to decrease the amount of times a bicycle that has been used filled in the text field.
     * @param event The fired event when the decrease button is pressed.
     */
    @FXML
    protected void decreaseBicycleUsage(ActionEvent event){
        if (tryParseInt(VegMeals.getText())) {
            this.bicycleUsed += Integer.parseInt(BicycleUsage.getText());
            Bicycle_Used.setText("I have used a bicycle today:"+ this.bicycleUsed);
        } else {
            generateAlert("You need to fill in a number!");
        }
    }

    /**
     * Used to increase the amount of times a bicycle that has been used filled in the text field.
     * @param event The fired event when the increase button is pressed.
     */
    @FXML
    protected void increaseBicycleUsage(ActionEvent event){
        if (tryParseInt(VegMeals.getText())) {
            this.bicycleUsed += Integer.parseInt(BicycleUsage.getText());
            Bicycle_Used.setText("I have used a bicycle today:"+ this.bicycleUsed);
        } else {
            generateAlert("You need to fill in a number!");
        }
    }

    /**
     * Tries to parse integer.
     *
     * @param value String that is to be parsed.
     * @return true iff the value provided is actual representation of integer.
     */
    private boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Generates dialog alert box with the message provided.
     *
     * @param msg the message that should alert the user
     */
    private void generateAlert(String msg){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Wrongly filled in info");
        alert.setContentText(msg);

        alert.showAndWait();
    }

}
