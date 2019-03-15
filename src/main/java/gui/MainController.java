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
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.GlyphFont;
import org.controlsfx.glyphfont.GlyphFontRegistry;
import utility.MainHandler;
import java.io.IOException;



/**
 * Controller for the main window.
 */
public class MainController {



    /**
     * Magic number 0.8.
     */
    private static final double POINT_EIGHT = 0.8;




    /**
     * The root of the FXML page.
     */
    @FXML
    private BorderPane root;



    /**
     * Bound to the home button on top.
     */
    @FXML
    private Button home;
    /**
     * Bound to the home button on top.
     */
    @FXML
    private Button personalInfo;




    /**
     * Method that is executed upon initializing of the corresponding FXML file.
     */
    @FXML
    public void initialize() {
        GlyphFont font = GlyphFontRegistry.font("FontAwesome");
        //CREATES ERRORS WHEN LOADING FROM OTHER TO HOME PAGE -> MIGHT HAVE TO SPLIT HOME AND MAIN CONTROLLER
        personalInfo.setGraphic(font.create(FontAwesome.Glyph.PENCIL));

        try {
            loadHomeScene();
        } catch (IOException err) {
            err.printStackTrace();
        }



    }



    /**
     * Loads how to play screen.
     * @throws IOException when FXMLLoader cannot load properly.
     */
    @FXML
    protected void loadHowToPlayScene()
            throws IOException {
        loadScene("howToPlay");
    }
    /**
     * Loads home screen.
     * @throws IOException when FXMLLoader cannot load properly.
     */
    @FXML
    protected  void loadHomeScene() throws IOException {
        home.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                new JackInTheBox(home).setResetOnFinished(true).play();
            }
        });
        loadScene("home");
    }

    /**
     * Loads about screen.
     * @throws IOException when FXMLLoader cannot load properly.
     */
    @FXML
    protected void loadAboutScene() throws IOException {
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
     * Loads a scene based on the given string.
     * @param scene The scene that needs to be loaded.
     * @throws IOException Thrown if
     * the FXMLLoader encounters an error
     */
    private void loadScene(final String scene)
            throws IOException {
        Parent newScene = FXMLLoader.load(getClass()
                .getResource("/fxml/" + scene + "Scene.fxml"));
        if (scene.equals("home")) {
            new ZoomIn(newScene).setSpeed(POINT_EIGHT).play();
        }

        root.setCenter(newScene);
    }

}
