package gui;

import animatefx.animation.FadeInUp;
import animatefx.animation.Pulse;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;
import utility.DbAdaptor;
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
     * Count of the pending requests.
     */
    int pendingReq = 0;


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



    private DbAdaptor dbAdaptor = new DbAdaptor();

    /**
     * Updates the Greeting in top right corner.
     * @param greetingstext The text to be updated.
     */

    public void setGreetingsText(final String greetingstext) {
        this.greetingsText.setText(greetingstext);
        this.greetingsText.setStyle("-fx-font-family: 'FontAwesome'");
    }

    /**
     * Methods for updating background dynamically.
     * @param score userScore.
     */

    public void changeBackground(int score) {

        BackgroundImage phazes;

        if (score >= 50000) {
            root.setStyle("-fx-background-image: url('/icons/backgroundPhase2_blur.png');-fx-background-size: cover;-fx-background-repeat: no-repeat;");
        } else if (score >= 100000) {
            root.setStyle("-fx-background-image: url('/icons/backgroundPhase3_blur.png');-fx-background-size: cover;-fx-background-repeat: no-repeat;");
        } else if (score >= 300000) {
            root.setStyle("-fx-background-image: url('/icons/backgroundPhase4_blur.png');-fx-background-size: cover;-fx-background-repeat: no-repeat;");
        } else if (score >= 500000) {
            root.setStyle("-fx-background-image: url('/icons/backgroundPhase5_blur.png');-fx-background-size: cover;-fx-background-repeat: no-repeat;");
        }
    }




    /**
     * Method that is executed upon initializing of the corresponding FXML file.
     */
    @FXML
    public void initialize() {
        try {
            loadHomeScene();
            updatePendingRequests();
            changeBackground(dbAdaptor.getTotalScore(MainHandler.username));
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    void updatePendingRequests() {
        dbAdaptor.connect();
        pendingReq = dbAdaptor.retrieveCount(MainHandler.username);
        if (pendingReq > 0) {
            loadFriendNotification(pendingReq);
        } else {
            friendsListBtn.setGraphic(null);
        }
    }

    /**
     * Sets the pending request notification on top.
     * @param notificationCount the number of pending requests.
     */
    private void loadFriendNotification(int notificationCount) {
        friendsListBtn.setGraphic(createNotification(String.valueOf(notificationCount)));
    }

    /**
     * Creates the notification bubble.
     * @param number the number of pending requests
     * @return returns a fancy bubble with a number inside
     */
    private Node createNotification(String number) {
        StackPane pane = new StackPane();
        String infoMsg = "You have "
                + pendingReq
                + " pending\n" + "friend requests";
        InformationBuilder informationBuilder = new InformationBuilder();
        informationBuilder.addInformativePopOverToNode(pane,infoMsg,
                PopOver.ArrowLocation.TOP_CENTER);
        Label lab = new Label(number);
        lab.setStyle("-fx-text-fill:white; -fx-font-size: 18");
        Circle cercle = new Circle(12, Color.rgb(41, 41, 41, .8));
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
                new Pulse(home).setResetOnFinished(true).play();
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

    /**
     * Enters fullScreen mode.
     * @param actionEvent
     */
    @FXML
    public void enterFullscreen(final ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource())
                .getScene().getWindow();
        if (!stage.isFullScreen()) {
            stage.setFullScreen(true);
        } else stage.setFullScreen(false);
    }

}
