package gui;

import animatefx.animation.BounceIn;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import org.controlsfx.control.PopOver;

/**
 * Controller for the personal info scene.
 */
public class personalInfoController {

    /**
     * Bound to the avatar box.
     */
    @FXML
    private HBox avatarImageBox;

    /**
     * Generated when necessary.
     */
    private PopOver popOver;

    /**
     * Bound to the change avatar button.
     */
    @FXML
    private Button changeAvatar;

    /**
     * Bound to the avatar image view.
     */
    @FXML
    private ImageView avatarImageView;

    /**
     * Bound to the change avatar label.
     */
    @FXML
    private Label infoChangeAvatarLabel;

    /**
     * Builds information labels.
     */
    @FXML
    private InformationBuilder informationBuilder = new InformationBuilder();


    /**
     * Executed upon initialization.
     */
    @FXML
    private void initialize(){
        informationBuilder
                .addInformationIconToSearchBox(infoChangeAvatarLabel,
                        "Drag the desired avatar\n"
                                + "to save the changes.");
        setupDragAndDropTarget(avatarImageBox);
    }

    /**
     * Sets up the drag and drop property.
     * @param avatarImageBox the box
     *                       where the drop is delivered.
     */
    private void setupDragAndDropTarget(HBox avatarImageBox) {
        avatarImageBox.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();

                if (db.hasImage()) {
                    event.acceptTransferModes(TransferMode.MOVE);
                }
                event.consume();
            }
        });
        avatarImageBox.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasImage()) {
                    avatarImageView.setImage(db.getImage());

                    popOver.hide();
                    new BounceIn(avatarImageBox).play();
                    event.setDropCompleted(true);
                } else {
                    event.setDropCompleted(false);
                }
            }
        });
    }


    /**
     * Changes avatar.
     * @param event Fired when the button is pressed.
     */
    @FXML
    public void changeAvatar(final ActionEvent event) {
        GridPane gridPane = new GridPane();

        for(int i = 0 ; i < 8 ; i++) {
            if(i<4) gridPane.add(createAvatar(i+1), i, 0);
            else gridPane.add(createAvatar(i+1), i % 4, 1 );
        }
        popOver = new PopOver(gridPane);
        popOver.show(changeAvatar);
    }


    /**
     * Creates an avatar.
     * @param numOfAvatar the number of the avatar
     * @return returns a button
     * with the image of avatar.
     */
    private Button createAvatar(int numOfAvatar) {
        Button avatar = new Button();
        ImageView avatarIv = new ImageView();
        Image avatarImage = new Image("/icons/avatar" + numOfAvatar + ".png");
        avatarIv.setImage(avatarImage);
        avatar.setGraphic(avatarIv);
//        avatar.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                Button btn = (Button) event.getSource();
//                ImageView imageView = (ImageView) btn.getGraphic();
//
//                avatarImageView.setImage(imageView.getImage());
//                popOver.hide();
//            }
//        });
        avatar.setOnDragDetected(new EventHandler <MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                /* allow any transfer mode */
                Dragboard db = avatar.startDragAndDrop(TransferMode.MOVE);

                /* put a image on dragboard */
                ClipboardContent content = new ClipboardContent();

                Image sourceImage = avatarIv.getImage();
                content.putImage(sourceImage);
                db.setContent(content);


                event.consume();
            }
        });
        avatar.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                avatar.setCursor(Cursor.OPEN_HAND);
            }
        });
        return avatar;
    }


}
