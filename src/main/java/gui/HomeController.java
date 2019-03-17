package gui;

import animatefx.animation.GlowBackground;
import animatefx.animation.GlowText;
import animatefx.animation.Pulse;
import features.Feature;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
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
     * Data about the points of the user.
     */
    private int pointsEarned = 0;
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
     * Bound to the points earned label.
     */
    @FXML
    private Label pointsEarnedLabel;

    public MainHandler handler = new MainHandler("http://localhost:8080");

    private static final int MINUS = -1;


    /**
     *
     */
    @FXML
    public void initialize() {
        //progressBarGreen.setProgress(PROGRESS_BAR_INIT_VAL);
    }
    /**
     * Used to decrease the amount of vegetarian meals filled in the text field.
     * @param event The fired event when the decrease button is pressed.
     */
    @FXML
    protected void decreaseVegetarianMeals(final ActionEvent event) {
        if (MainHandler.tryParseInt(vegMeals.getText())) {
            int valVegMeals = Integer.parseInt(vegMeals.getText());
            if (MainHandler.checkPositiveValues(this.vegetarianMeals,
                    valVegMeals)) {
                Feature meal = new Feature(vegMeals.getText());

                this.vegetarianMeals = handler.updateVegMeal(MINUS*valVegMeals);
                try {
                    this.pointsEarned = handler.getTotalScore();
                } catch(Exception e) {
                    new AlertBuilder().displayException(e);
                }
                pointsEarnedLabel.setText("Points earned:"
                        + this.pointsEarned);

                onUpdatePointsEarnedLabel(Color.WHITE,Color.RED);

            } else {
                ALERT_BUILDER
                        .formEntryWarning(vegMealsEaten.getText(),
                                "This value cannot be negative.");
            }
        } else {
            ALERT_BUILDER
                    .formEntryWarning(vegMealsEaten.getText(),
                    YOU_NEED_TO_FILL_A_NUMBER);
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

            this.vegetarianMeals = handler.updateVegMeal(Integer.parseInt(vegMeals.getText()));
            try {
                this.pointsEarned = handler.getTotalScore();
            } catch(Exception e) {
                new AlertBuilder().displayException(e);
            }

            pointsEarnedLabel.setText("Points earned: "
                    + this.pointsEarned);

            ALERT_BUILDER
                    .showInformationNotification(
                            "Good job! Keep on going greener!");

            onUpdatePointsEarnedLabel(Color.WHITE,Color.LIGHTGREEN);

        } else {
            ALERT_BUILDER
                    .formEntryWarning(vegMealsEaten.getText(),
                            YOU_NEED_TO_FILL_A_NUMBER);
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
            Feature meal = new Feature(bicycleUsage.getText());
            int bicycleUse = Integer.parseInt(bicycleUsage.getText());
            if (MainHandler.checkPositiveValues(this.bicycleUsed,
                    bicycleUse)) {
                this.bicycleUsed -= bicycleUse;

                this.pointsEarned -= meal.calculatePoints(2);
                this.pointsEarnedLabel
                        .setText("Points earned: " + this.pointsEarned);

                onUpdatePointsEarnedLabel(Color.WHITE,Color.RED);

            } else {
                ALERT_BUILDER
                        .formEntryWarning(bicycleUsedLabel.getText(),
                                "This value cannot be negative.");
            }
        } else {
            ALERT_BUILDER
                    .formEntryWarning(bicycleUsedLabel.getText(),
                            YOU_NEED_TO_FILL_A_NUMBER);
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
            Feature meal = new Feature(bicycleUsage.getText());
            this.bicycleUsed += Integer.parseInt(bicycleUsage.getText());
            this.pointsEarned += meal.calculatePoints(2);
            this.pointsEarnedLabel
                    .setText("Points earned: " + this.pointsEarned);

            ALERT_BUILDER
                    .formNotification("Good job! Keep on going greener!")
                    .showInformation();

            onUpdatePointsEarnedLabel(Color.WHITE,Color.LIGHTGREEN);

        } else {
            ALERT_BUILDER
                    .formEntryWarning(bicycleUsedLabel.getText(),
                            YOU_NEED_TO_FILL_A_NUMBER);
        }
    }

    private void glowPointsEarnedLabel(Color c1, Color c2) {
        new GlowText(this.pointsEarnedLabel, c1, c2).play();
    }

    private void onUpdatePointsEarnedLabel(Color c1,Color c2) {
        new Pulse(this.pointsEarnedLabel).play();
        glowPointsEarnedLabel(c1, c2);

    }

}
