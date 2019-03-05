package gui;

import features.VegetarianMeal;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import java.io.IOException;

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
     * Alert text.
     */
    private static final String YOU_NEED_TO_FILL_A_NUMBER
            = "You need to fill a number!";
    /**
     * Magic number 250.
     */
    private static final int TWO_HUNDRED_FIFTY = 250;

    /**
     * Magic number 0.7.
     */
    private static final double PROGRESS_BAR_INIT_VAL = 0.7;
    /**
     * The root of the FXML page.
     */
    @FXML
    private BorderPane root;

    /**
     * Bound to the Progress bar
     * indicating the progress of the user.
     */
    @FXML
    private ProgressBar progressBarGreen;
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
     * Method that is executed upon initializing of the corresponding FXML file.
     */
    @FXML
    public void initialize() {
        progressBarGreen.setProgress(PROGRESS_BAR_INIT_VAL);
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        progressBarGreen.setPrefWidth(bounds.getWidth() - TWO_HUNDRED_FIFTY);
    }

    /**
     * Loads how to play screen.
     * @param event The event that is fired when the button is clicked.
     * @throws IOException when FXMLLoader cannot load properly.
     */
    @FXML
    protected void loadHowToPlayScene(final ActionEvent event)
            throws IOException {
        FXMLLoader loader = new FXMLLoader();
        VBox newScene = loader.load(getClass()
                .getResource("/howToPlay.fxml"));
        root.setCenter(newScene);
    }
    /**
     * Loads home screen.
     * @param event The event that is fired when the button is clicked.
     * @throws IOException when FXMLLoader cannot load properly.
     */
    @FXML
    protected  void loadHomeScene(final ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        ListView newScene = loader.load(getClass().getResource("/home.fxml"));
        root.setCenter(newScene);
    }
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


            VegetarianMeal meal = new VegetarianMeal(vegMeals.getText());
            System.out.println(meal.toString());

            this.vegetarianMeals += meal.calculatePoints();

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
