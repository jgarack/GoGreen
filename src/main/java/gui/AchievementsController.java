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

import java.util.List;

/**
 * Controller for the achievements page.
 */
public class AchievementsController {
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
     * Used to show useful information to the user.
     */
    @FXML
    private PopOver popOver;

    /**
     * Triggered upon initialization.
     */
    @FXML
    public void initialize() {
        setUpAchievementBoxes();
    }

    /**
     * Sets up the Boxes.
     */
    private void setUpAchievementBoxes() {

        List<Achievement> achievements = dbAdaptor.getAllAchievements();
        int achievementCounter = 0;
        for (Achievement achievement : achievements) {
            System.out.println(achievement.getName() + " -> " + achievement.getDescription());
           createVBoxAchievement(achievement, achievementCounter);
           achievementCounter++;

        }


    }

    /**
     * Creates an individual box.
     * @param achievement The achievement.
     * @param achievementCounter keeps track of the achievements.
     */
    private void createVBoxAchievement(Achievement achievement, int achievementCounter) {
        if (achievementCounter < 4) {
            VBox currVBox = new VBox();
            ImageView currImgView = new ImageView();
            currImgView.setImage(new Image("/icons/achievement" + (achievementCounter + 1) + ".png"));
            Label currTitle = new Label(achievement.getName());
            Label currDescription = new Label(achievement.getDescription());
            currTitle.getStyleClass().add("achTitle");
            currDescription.getStyleClass().add("achDescription");
            currVBox.getChildren().addAll(currTitle,currImgView);
            currVBox.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    popOver = new PopOver(currDescription);
                    popOver.show(currVBox);
                }
            });
            currVBox.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    popOver.hide();
                }
            });
            currVBox.setAlignment(Pos.CENTER);
            grid.add(currVBox,achievementCounter  % 4, achievementCounter / 4);
        }
    }


}
