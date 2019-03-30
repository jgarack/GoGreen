package gui;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
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

    private static final int COLS = 4;

    /**
     * Used for full opacity.
     */
    private static final double FULL_OPACITY = 1.0;

    /**
     * Used for half opacity.
     */
    private static final double HALF_OPACITY = 0.5;

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
     * Bound to the achievement.
     */
    @FXML
    private ImageView achievement1;
    /**
     * Bound to the achievement.
     */
    @FXML
    private ImageView achievement2;
    /**
     * Bound to the achievement.
     */
    @FXML
    private ImageView achievement3;
    /**
     * Bound to the achievement.
     */
    @FXML
    private ImageView achievement4;
    /**
     * Bound to the achievement.
     */
    @FXML
    private ImageView achievement5;
    /**
     * Bound to the achievement.
     */
    @FXML
    private ImageView achievement6;

    /**
     * Bound to the achievement.
     */
    @FXML
    private ImageView achievement7;

    /**
     * Bound to the achievement.
     */
    @FXML
    private ImageView achievement8;

    /**
     * Bound to the achievement.
     */
    @FXML
    private ImageView achievement9;
    /**
     * Bound to the achievement.
     */
    @FXML
    private ImageView achievement10;
    /**
     * Bound to the achievement.
     */
    @FXML
    private ImageView achievement11;
    /**
     * Bound to the achievement.
     */
    @FXML
    private ImageView achievement12;

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
        popOver.setId("popOver");
        List<Achievement> allAchievements = dbAdaptor.getAllAchievements();
        int achievementCounter = 0;
        List<Integer> achievementIds =
                dbAdaptor.getAchievements(MainHandler.username);
        for (Achievement currAch : allAchievements) {
            createVBoxAchievement(currAch,
                    achievementCounter, achievementIds);
            achievementCounter++;
        }
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
            currImgView.setOpacity(HALF_OPACITY);
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
                achievementCounter / COLS);

    }


}
