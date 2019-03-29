package gui;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.PopOver;
import org.omg.PortableInterceptor.INACTIVE;
import utility.Achievement;
import utility.DbAdaptor;
import utility.MainHandler;

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

        List<Achievement> achievementList = dbAdaptor.getAllAchievements();
        int achievementCounter = 0;
        for (Node currNode : grid.getChildren()) {
            VBox currBox = (VBox) currNode;
            Label currTitle = (Label) currBox.getChildren().get(0);
            currTitle.setText(achievementList.get(achievementCounter).getName());

            Label currDescription = new Label(achievementList.get(achievementCounter).getDescription());
            currDescription.getStyleClass().add("achDescription");
            currBox.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    popOver = new PopOver(currDescription);
                    popOver.show(currBox);
                }
            });
            currBox.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    popOver.hide();
                }
            });

            achievementCounter++;
        }
        List<Integer> achieved = dbAdaptor.getAchievements(MainHandler.username);
        for (Integer id : achieved) {
            if (id == 1) {
                achievement1.setOpacity(0.5);
            } else if ( id == 2) {
                achievement2.setOpacity(0.5);
            }

        }


    }

//    /**
//     * Creates an individual box.
//     * @param achievement The achievement.
//     * @param achievementCounter keeps track of the achievements.
//     */
//    private void createVBoxAchievement(Achievement achievement, int achievementCounter) {
//        if (achievementCounter < 12) {
//            VBox currVBox = new VBox();
//            ImageView currImgView = new ImageView();
//            currImgView.setImage(new Image("/icons/achievement" + (achievementCounter + 1) + ".png"));
//            currImgView.setId("achievement" + achievementCounter);
//            Label currTitle = new Label(achievement.getName());
//            Label currDescription = new Label(achievement.getDescription());
//            currTitle.getStyleClass().add("achTitle");
//            currDescription.getStyleClass().add("achDescription");
//            currVBox.getChildren().addAll(currTitle,currImgView);
//            currVBox.setOnMouseEntered(new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent event) {
//                    popOver = new PopOver(currDescription);
//                    popOver.show(currVBox);
//                }
//            });
//            currVBox.setOnMouseExited(new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent event) {
//                    popOver.hide();
//                }
//            });
//            currVBox.setAlignment(Pos.CENTER);
//            grid.add(currVBox,achievementCounter  % 4, achievementCounter / 4);
//        }
//    }


}
