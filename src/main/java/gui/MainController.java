package gui;

import features.VegetarianMeal;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controller for the main window.
 */
public class MainController {

    /**
     * Data about vegetarian meals consumed
     * that is to be retrieved from database.
     */
    private int vegetarianMeals = 0;
    /**
     * Data about the times that a bicycle
     * has been used that is to be retrieved from database.
     */
    private int bicycleUsed = 0;
    /**
     *
     */
    private static final String YOU_NEED_TO_FILL_A_NUMBER
            = "You need to fill a number!";

    /**
     * Bound to the text field linked with vegetarian meals.
     */
    @FXML
    private TextField vegMeals;
    /**
     * Bound to the label about the vegetarian meals
     * (used to show progress).
     */
    @FXML
    private Label vegMealsEaten;

    /**
     * Bound to the text field about the usage of bicycle.
     */
    @FXML
    private TextField bicycleUsage;
    /**
     * Bound to the bicycle label - used to show progress.
     */
    @FXML
    private Label bicycleUsedLabel;

    /**
     * Used to decrease the amount of vegetarian meals filled in the text field.
     * @param event The fired event when the decrease button is pressed.
     */
    @FXML
    protected void decreaseVegetarianMeals(final ActionEvent event) {
            if (tryParseInt(vegMeals.getText())) {
                this.vegetarianMeals -= Integer.parseInt(vegMeals.getText());
                vegMealsEaten.setText("Vegetarian meals eaten:"
                        + this.vegetarianMeals);
            } else {
                generateAlert(YOU_NEED_TO_FILL_A_NUMBER);
            }
    }

    /**
     * Used to increase the amount of vegetarian meals filled in the text field.
     * @param event The fired event when the increase button is pressed.
     */
    @FXML
    protected void increaseVegetarianMeals(final ActionEvent event) {
        if (tryParseInt(vegMeals.getText())) {

            VegetarianMeal meal = new VegetarianMeal(vegMeals);
            System.out.println(meal.toString());

            //this.vegetarianMeals += meal.calculatePoints(event);

            vegMealsEaten.setText("Points earned:"
                    + this.vegetarianMeals);

        } else {
            generateAlert(YOU_NEED_TO_FILL_A_NUMBER);
        }
    }

    /**
     * Used to decrease the amount of times a bicycle
     * that has been used filled in the text field.
     * @param event The fired event when the decrease button is pressed.
     */
    @FXML
    protected void decreaseBicycleUsage(final ActionEvent event) {
        if (tryParseInt(vegMeals.getText())) {
            this.bicycleUsed += Integer.parseInt(bicycleUsage.getText());
            bicycleUsedLabel.setText("I have used a bicycle today:"
                    + this.bicycleUsed);
        } else {
            generateAlert(YOU_NEED_TO_FILL_A_NUMBER);
        }
    }

    /**
     * Used to increase the amount of times a bicycle
     * that has been used filled in the text field.
     * @param event The fired event when the increase button is pressed.
     */
    @FXML
    protected void increaseBicycleUsage(final ActionEvent event) {
        if (tryParseInt(vegMeals.getText())) {
            this.bicycleUsed += Integer.parseInt(bicycleUsage.getText());
            bicycleUsedLabel.setText("I have used a bicycle today:"
                    + this.bicycleUsed);
        } else {
            generateAlert("Wrong format!");
        }
    }

    /**
     * Tries to parse integer.
     *
     * @param value String that is to be parsed.
     * @return true iff the value provided is actual representation of integer.
     */
    private boolean tryParseInt(final String value) {
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
    private void generateAlert(final String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Wrongly filled in info");
        alert.setContentText(msg);

        alert.showAndWait();
    }

}
