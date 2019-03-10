package gui;

import animatefx.animation.ZoomIn;
import animatefx.animation.JackInTheBox;
import features.Feature;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
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
     * Magic number 300.
     */
    private static final int THREE_HUNDRED = 300;
    /**
     * Magic number 0.8.
     */
    private static final double POINT_EIGHT = 0.8;



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
     * Bound to the home button on top.
     */
    @FXML
    private Button home;

    /**
     * Bound to the list of goGreen features.
     */
    @FXML
    private ListView featuresList;

    /**
     * Method that is executed upon initializing of the corresponding FXML file.
     */
    @FXML
    public void initialize() {
        progressBarGreen.setProgress(PROGRESS_BAR_INIT_VAL);
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        progressBarGreen.setPrefWidth(bounds.getWidth() - TWO_HUNDRED_FIFTY);

        featuresList.setPrefSize(bounds.getWidth() - THREE_HUNDRED,
                bounds.getHeight());
    }

    /**
     * Loads how to play screen.
     * @param event The event that is fired when the button is clicked.
     * @throws IOException when FXMLLoader cannot load properly.
     */
    @FXML
    protected void loadHowToPlayScene(final ActionEvent event)
            throws IOException {
        loadScene("howToPlay");
    }
    /**
     * Loads home screen.
     * @param event The event that is fired when the button is clicked.
     * @throws IOException when FXMLLoader cannot load properly.
     */
    @FXML
    protected  void loadHomeScene(final ActionEvent event) throws IOException {
        home.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                new JackInTheBox(home).setResetOnFinished(true).play();
            }
        });
        loadScene("home");
    }

    /**
     * Loads home screen.
     * @param event The event that is fired when the button is clicked.
     * @throws IOException when FXMLLoader cannot load properly.
     */
    @FXML
    protected void loadAboutScene(final ActionEvent event) throws IOException {
        loadScene("about");
    }

    /**
     * Loads home screen.
     * @param event The event that is fired when the button is clicked.
     * @throws IOException when FXMLLoader cannot load properly.
     */
    @FXML
    protected void loadReviewScene(final ActionEvent event) throws IOException {
        loadScene("review");
    }
    /**
     * Used to decrease the amount of vegetarian meals filled in the text field.
     * @param event The fired event when the decrease button is pressed.
     */
    @FXML
    protected void decreaseVegetarianMeals(final ActionEvent event) {
            if (tryParseInt(vegMeals.getText())) {

                Feature meal = new Feature(vegMeals.getText());
                System.out.println(meal.toString());

                this.vegetarianMeals -= meal.calculatePoints(1);

                vegMealsEaten.setText("CO2/kg Extra Emissions:"
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


            Feature meal = new Feature(vegMeals.getText());
            System.out.println(meal.toString());

            this.vegetarianMeals += meal.calculatePoints(1);

            vegMealsEaten.setText("CO2/kg saved:"
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
        if (tryParseInt(bicycleUsage.getText())) {
            Feature bike = new Feature(bicycleUsage.getText());
            System.out.println(bike.toString());
            this.bicycleUsed -= bike.calculatePoints(2);
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
        if (tryParseInt(bicycleUsage.getText())) {
            Feature bike = new Feature(bicycleUsage.getText());
            System.out.println(bike.toString());
            this.bicycleUsed += bike.calculatePoints(2);
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

    /**
     * Loads a scene based on the given string.
     * @param scene The scene that needs to be loaded.
     * @throws IOException Thrown if
     * the FXMLLoader encounters an error
     */
    private void loadScene(final String scene)
            throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent newScene = loader.load(getClass()
                .getResource("/" + scene + "Scene.fxml"));
        switch (scene) {
            case"home": {
                System.out.println("here");
                new ZoomIn(newScene).setSpeed(POINT_EIGHT).play();
                break;
            }
            default: {
                break;
            }
        }
        root.setCenter(newScene);
    }

}
