package gui;

import animatefx.animation.ZoomIn;
import animatefx.animation.JackInTheBox;
import features.VegetarianMeal;
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
     * Bound to the list of goGreen features.
     */
    @FXML
    private ListView featuresList;



    /**
     * Method that is executed upon initializing of the corresponding FXML file.
     */
    @FXML
    public void initialize() {
//        progressBarGreen.setProgress(PROGRESS_BAR_INIT_VAL);
//        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
//        progressBarGreen.setPrefWidth(bounds.getWidth() - TWO_HUNDRED_FIFTY);


        GlyphFont font = GlyphFontRegistry.font("FontAwesome");
        //CREATES ERRORS WHEN LOADING FROM OTHER TO HOME PAGE -> MIGHT HAVE TO SPLIT HOME AND MAIN CONTROLLER
        personalInfo.setGraphic(font.create(FontAwesome.Glyph.PENCIL));

        try{
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
     * Loads a scene based on the given string.
     * @param scene The scene that needs to be loaded.
     * @throws IOException Thrown if
     * the FXMLLoader encounters an error
     */
    private void loadScene(final String scene)
            throws IOException {
        Parent newScene = FXMLLoader.load(getClass()
                .getResource("/" + scene + "Scene.fxml"));
        if (scene.equals("home")) {
            new ZoomIn(newScene).setSpeed(POINT_EIGHT).play();
        }

        root.setCenter(newScene);
    }

}
