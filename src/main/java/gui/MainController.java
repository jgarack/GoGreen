package gui;

import animatefx.animation.FadeInUp;
import animatefx.animation.JackInTheBox;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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
     * The fond size.
     * {@value}
     */
    private static final int FSIZE = 20;




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
     * Bound to the home button on top.
     */
    @FXML
    private Button friendsListBtn;


    /**
     * Bound to the greetings label on top.
     */
    @FXML
    private Label greetingsText;

    private static final int pendingReq = 0;

    /**
     * Updates the Greeting in top right corner.
     * @param greetingstext The text to be updated.
     */

    public void setGreetingsText(final String greetingstext) {
        this.greetingsText.setText(greetingstext);
        this.greetingsText.setStyle("-fx-font-family: 'FontAwesome'");
    }




    /**
     * Method that is executed upon initializing of the corresponding FXML file.
     */
    @FXML
    public void initialize() {
        try {
            loadHomeScene();
            if (pendingReq > 0) {
                loadFriendNotification(pendingReq);
            }
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    private void loadFriendNotification(int notificationCount) {
        friendsListBtn.setGraphic(createNotification(String.valueOf(notificationCount)));
    }

    private Node createNotification(String number) {
        StackPane pane = new StackPane();
        Label lab = new Label(number);
        lab.setStyle("-fx-text-fill:white");
        Circle cercle = new Circle(10, Color.rgb(5, 200, 0, .9));
        cercle.setStrokeWidth(2.0);
        cercle.setStyle("-fx-background-insets: 0 0 -1 0, 0, 1, 2;");
        cercle.setSmooth(true);
        pane.getChildren().addAll(cercle, lab);
        return pane;
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
     * Loads friends list scene.
     * @throws IOException when FXMLLoader cannot load properly.
     */
    @FXML
    protected void loadFriendsListScene() throws IOException {
        loadScene("friendsList");
    }

    /**
     * Loads achievements scene.
     * @throws IOException when FXMLLoader cannot load properly.
     */
    @FXML
    protected void loadAchievementsScene() throws IOException {
        MainHandler.achievementsUsername = null;
        loadScene("achievements");
    }

    /**
     * Loads achievements scene for a friend.
     * @param username the friend.
     * @throws IOException when FXMLLoader cannot load properly.
     */
    void loadAchievementsScene(String username) throws IOException {
        MainHandler.achievementsUsername =  username;
        loadScene("achievements");
    }

    /**
     * Loads a scene based on the given string.
     * @param scene The scene that needs to be loaded.
     * @throws IOException Thrown if the FXMLLoader encounters an error
     */
    private void loadScene(final String scene)
            throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/fxml/" + scene + "Scene.fxml"));
        Parent newScene = fxmlLoader.load();
        new FadeInUp(newScene).play();
        if (scene.equals("personalInfo")) {
            ((PersonalInfoController) fxmlLoader.getController())
                    .setMainController(this);
        } else if (scene.equals("friendsList")) {
            ((FriendsController) fxmlLoader.getController())
                    .setMainController(this);
        }
        root.setCenter(newScene);
    }


}
