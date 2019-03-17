package gui;

import animatefx.animation.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
//import org.controlsfx.glyphfont.FontAwesome;
//import org.controlsfx.glyphfont.GlyphFont;
//import org.controlsfx.glyphfont.GlyphFontRegistry;
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
        //GlyphFont font = GlyphFontRegistry.font("FontAwesome");
        //personalInfo.setGraphic(font
        // .create(FontAwesome.Glyph.PENCIL).size(16));

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
     * Loads screen with personal info.
     * @throws IOException when FXMLLoader cannot load properly.
     */
    @FXML
    protected void loadPersonalInfoScene() throws IOException {
        loadScene("personalInfo");
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
        } else if (scene.equals("howToPlay")) {
            new SlideInRight(newScene).play();
        } else if (scene.equals("about")) {
            new FadeInRight(newScene).play();
        }

        root.setCenter(newScene);
    }

}
