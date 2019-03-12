package gui;

import features.VegetarianMeal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import utility.MainHandler;

/**
 * Controller for the home page.
 */
public class HomeController {
    /**
     * Alert text.
     */
    private static final String YOU_NEED_TO_FILL_A_NUMBER
            = "You need to fill a number!";
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
     * The builder used to build alerts for this handler.
     */
    private static final AlertBuilder alert = new AlertBuilder();
    /**
     * Used to decrease the amount of vegetarian meals filled in the text field.
     * @param event The fired event when the decrease button is pressed.
     */
    @FXML
    protected void decreaseVegetarianMeals(final ActionEvent event) {
        if (MainHandler.tryParseInt(vegMeals.getText())) {
            this.vegetarianMeals -= Integer.parseInt(vegMeals.getText());
            vegMealsEaten.setText("Vegetarian meals eaten:"
                    + this.vegetarianMeals);
        } else {
            alert.formEntryWarning(vegMealsEaten.getText(),YOU_NEED_TO_FILL_A_NUMBER).show();
        }
    }

    /**
     * Used to increase the amount of vegetarian meals filled in the text field.
     * @param event The fired event when the increase button is pressed.
     */
    @FXML
    protected void increaseVegetarianMeals(final ActionEvent event) {
        if (MainHandler.tryParseInt(vegMeals.getText())) {


            VegetarianMeal meal = new VegetarianMeal(vegMeals.getText());
            System.out.println(meal.toString());

            this.vegetarianMeals += meal.calculatePoints();

            vegMealsEaten.setText("Points earned:"
                    + this.vegetarianMeals);

        } else {
            alert.formEntryWarning(vegMealsEaten.getText(),YOU_NEED_TO_FILL_A_NUMBER).show();
        }
    }

    /**
     * Used to decrease the amount of times a bicycle
     * that has been used filled in the text field.
     * @param event The fired event when the decrease button is pressed.
     */
    @FXML
    protected void decreaseBicycleUsage(final ActionEvent event) {
        if (MainHandler.tryParseInt(bicycleUsage.getText())) {

            this.bicycleUsed -= Integer.parseInt(bicycleUsage.getText());
            bicycleUsedLabel.setText("Times you have used bicycle instead of a car:"
                    + this.bicycleUsed);
        } else {
            alert.formEntryWarning(bicycleUsedLabel.getText(),YOU_NEED_TO_FILL_A_NUMBER).show();
        }
    }
    /**
     * Used to increase the amount of times a bicycle
     * that has been used filled in the text field.
     * @param event The fired event when the increase button is pressed.
     */
    @FXML
    protected void increaseBicycleUsage(final ActionEvent event) {
        if (MainHandler.tryParseInt(bicycleUsage.getText())) {
            this.bicycleUsed += Integer.parseInt(bicycleUsage.getText());
            bicycleUsedLabel.setText("Times you have used bicycle instead of a car:"
                    + this.bicycleUsed);
        } else {
            alert.formEntryWarning(bicycleUsedLabel.getText(),YOU_NEED_TO_FILL_A_NUMBER).show();
        }
    }

}
