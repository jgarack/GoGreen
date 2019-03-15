package gui;

import animatefx.animation.Pulse;
import features.Feature;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
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
     * Magic number 0.7.
     */
    private static final double PROGRESS_BAR_INIT_VAL = 0.7;
    /**
     * Magic number 300.
     */
    private static final int THREE_HUNDRED = 300;
    /**
     * Magic number 250.
     */
    private static final int TWO_HUNDRED_FIFTY = 250;
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
     * Bound to the Progress bar
     * indicating the progress of the user.
     */
    @FXML
    private ProgressBar progressBarGreen;

    /**
     * The builder used to build alerts for this handler.
     */
    private static final AlertBuilder ALERT_BUILDER = new AlertBuilder();
    /**
     * Bound to the list of goGreen features.
     */
    @FXML
    private ListView featuresList;


    /**
     *
     */
    @FXML
    public void initialize() {
        progressBarGreen.setProgress(PROGRESS_BAR_INIT_VAL);
    }
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
            ALERT_BUILDER
                    .formEntryWarning(vegMealsEaten.getText(),
                    YOU_NEED_TO_FILL_A_NUMBER).show();
        }
    }

    /**
     * Used to increase the amount of vegetarian meals filled in the text field.
     * @param event The fired event when the increase button is pressed.
     */
    @FXML
    protected void increaseVegetarianMeals(final ActionEvent event) {
        if (MainHandler.tryParseInt(vegMeals.getText())) {


            Feature meal = new Feature(vegMeals.getText());
            System.out.println(meal.toString());

            this.vegetarianMeals += meal.calculatePoints(2);

            vegMealsEaten.setText("Points earned:"
                    + this.vegetarianMeals);

            new Pulse(this.vegMealsEaten).play();

        } else {
            ALERT_BUILDER
                    .formEntryWarning(vegMealsEaten.getText(),
                            YOU_NEED_TO_FILL_A_NUMBER).show();
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
            bicycleUsedLabel
                    .setText("Times you have used bicycle instead of a car:"
                    + this.bicycleUsed);
        } else {
            ALERT_BUILDER
                    .formEntryWarning(bicycleUsedLabel.getText(),
                            YOU_NEED_TO_FILL_A_NUMBER).show();
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
            bicycleUsedLabel
                    .setText("Times you have used bicycle instead of a car:"
                    + this.bicycleUsed);

            new Pulse(this.bicycleUsedLabel).play();
        } else {
            ALERT_BUILDER
                    .formEntryWarning(bicycleUsedLabel.getText(),
                            YOU_NEED_TO_FILL_A_NUMBER).show();
        }
    }

}
