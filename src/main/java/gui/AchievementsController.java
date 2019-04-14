package gui;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.PopOver;
import utility.Achievement;
import utility.DbAdaptor;
import utility.MainHandler;

import java.util.List;

/**
 * Controller for the achievements page.
 */
public class AchievementsController {

    /**
     * Columns constant.
     */
    private static final int COLS = 4;

    /**
     * Used for full opacity.
     */
    private static final double FULL_OPACITY = 1.0;

    /**
     * Used for lowest opacity.
     */
    private static final double LOW_OPACITY = 0.2;

    /**
     * To retrieve all the achievements.
     */
    private DbAdaptor dbAdaptor = new DbAdaptor();
    /**
     * The root of the scene.
     */
    @FXML
    private GridPane grid;

    /**
     * The user
     * whose achievements are requested.
     */
    private String username;



    /**
     * Used to show useful information to the user.
     */
    @FXML
    private PopOver popOver;


    /**
     * Triggered upon loading of scene.
     */
    @FXML
    public void initialize() {
        if (MainHandler.achievementsUsername == null) {
            System.out.println("username is null");
            setUsername(MainHandler.username);
        } else {
            setUsername(MainHandler.achievementsUsername);
        }
        List<Achievement> allAchievements = dbAdaptor.getAllAchievements();
        int achievementCounter = 0;
        List<Integer> achievementIds =
                dbAdaptor.getAchievements(username);
        createHeader(username);
        for (Achievement currAch : allAchievements) {
            createVBoxAchievement(currAch,
                    achievementCounter, achievementIds);
            achievementCounter++;
        }
    }

    /**
     * Creates a header for the user.
     * @param user the searched user.
     */
    private void createHeader(final String user) {
        String title = "";
        if (user.equals(MainHandler.username)) {
            title = "Your achievements";
        } else {
            title = "Achievements of " + user;
        }
        Label header = new Label(title);
        header.setId("header");
        grid.add(header, 0, 0, COLS, 1);
        GridPane.setHalignment(header, HPos.CENTER);
    }

    /**
     * Creates an individual box.
     * @param achievement The achievement.
     * @param achievementCounter keeps
     *                         track of the achievements.
     * @param achievmentIds ids of the
     *                      achieved achievements.
     */
    private void
        createVBoxAchievement(final Achievement achievement,
                                       final int achievementCounter,
                                       final List<Integer> achievmentIds) {
        ImageView currImgView = new ImageView();
        currImgView
                .setImage(new Image("/icons/achievement"
                + (achievementCounter + 1) + ".png"));
        currImgView.setId("achievement" + achievementCounter);
        if (achievmentIds.contains(achievementCounter + 1)) {
            currImgView.setOpacity(FULL_OPACITY);
        } else {
            currImgView.setOpacity(LOW_OPACITY);
        }

        VBox currVBox = new VBox();

        Label currTitle = new Label(achievement.getName());
        Label currDescription = new Label(achievement.getDescription());
        currTitle.getStyleClass().add("achTitle");
        currDescription.getStyleClass().add("achDescription");
        currVBox.getChildren().addAll(currTitle, currImgView);
        currVBox.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                popOver = new PopOver(currDescription);
                popOver.setId("popOver");
                popOver.show(currVBox);
            }
        });
        currVBox.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                popOver.hide();
            }
        });
        currVBox.setAlignment(Pos.CENTER);
        grid.add(currVBox,
                achievementCounter  %  COLS,
                (achievementCounter / COLS) + 1);

    }

    /**
     * Sets the current user.
     * @param user the current user.
     */
    public void setUsername(final String user) {
        this.username = user;
    }

}
