package gui;

import animatefx.animation.BounceIn;
import animatefx.animation.ZoomIn;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import org.controlsfx.control.PopOver;
import utility.DbAdaptor;
import utility.MainHandler;

import java.io.IOException;


/**
 * Controller for the personal info scene.
 */
public class personalInfoController {


    private MainController mainController;

    /**
     * Bound to the root.
     */
    @FXML
    private GridPane grid;

    /**
     * Bound to the old pass field.
     */
    @FXML
    private PasswordField oldPass;

    /**
     * Bound to the new pass field.
     */
    @FXML
    private PasswordField newPass;
    /**
     * Bound to the confirm new pass field.
     */
    @FXML
    private PasswordField confirmNewPass;
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
     * Generated when extra help is needed.
     */
    private PopOver helperPopOver;

    /**
     * For building alerts.
     */
    private AlertBuilder alertBuilder = new AlertBuilder();
    /**
     * Bound to the change avatar button.
     */
    @FXML
    private Button selectAvatar;

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
     * String holder for avatar url.
     */
    private String avatarUrl = "/icons/avatar1.png";

    /**
     * Builds information labels.
     */
    @FXML
    private InformationBuilder informationBuilder = new InformationBuilder();

    private DbAdaptor dbAdaptor = new DbAdaptor();

    private Image img = new Image(dbAdaptor.getUser(MainHandler.username).getAvatarUrl());



    /**
     * Executed upon initialization.
     */
    @FXML
    private void initialize() {
        informationBuilder
                .addInformationIconToSearchBox(infoChangeAvatarLabel,
                        "Drag the desired avatar\n"
                                + "to save the changes.");
        setUpAvatar();
        setupDragAndDropTarget(avatarImageBox);
    }


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Sets up the avatar of the user.
     */
    private void setUpAvatar() {
        System.out.println(dbAdaptor.getUser(MainHandler.username).getAvatarUrl());
        avatarImageBox.setMaxSize(120,120);
        avatarImageBox.setPrefSize(120,120);
        avatarImageBox.setMinSize(120,120);
        avatarImageView.setPreserveRatio(false);
        avatarImageView.fitWidthProperty().bind(avatarImageBox.widthProperty());
        avatarImageView.fitHeightProperty().bind(avatarImageBox.heightProperty());
        avatarImageView.setImage(img);
        avatarImageView.setVisible(true);
        System.out.println(avatarImageView.getImage());
    }

    /**
     * Sets up the drag and drop property.
     * @param avatarImageBox the box
     *                       where the drop is delivered.
     */
    private void setupDragAndDropTarget(HBox avatarImageBox) {
        grid.setOnDragEntered(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasImage()) {
                    Label label = new Label("Drop here");
                    label.setId("dropHereLabel");
                    helperPopOver = new PopOver(label);
                    helperPopOver.setCornerRadius(2);
                    helperPopOver.show(avatarImageBox);
                    //new Pulse(label).play();
                }
            }
        });
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
                    if (db.hasUrl() &&  db.getUrl().contains("imgur.com")
                            &&  db.getUrl().contains("https")) {

                        avatarUrl = db.getUrl();
                        avatarImageView.setImage(db.getImage());
                        new BounceIn(avatarImageBox).play();
                        event.setDropCompleted(true);
                    } else {
                        alertBuilder.showAlertNotification("Avatar update failed."
                                + "\nTry dragging another image.");
                    }
                } else {
                    event.setDropCompleted(false);
                    alertBuilder.showAlertNotification("Avatar update failed."
                            + "\nTry dragging another image.");
                }
                helperPopOver.hide();
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

        for (int i = 0 ; i < 8 ; i++) {
            if (i < 4) {
                gridPane.add(createAvatar(i + 1), i, 0);
            } else {
                gridPane.add(createAvatar(i + 1), i % 4, 1 );
            }
        }
        popOver = new PopOver(gridPane);
        popOver.show(selectAvatar);
    }


    /**
     * Creates an avatar.
     * @param numOfAvatar the number of the avatar
     * @return returns a button with the image of avatar.
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
        avatar.setOnDragDetected(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                /* allow any transfer mode */
                Dragboard db = avatar.startDragAndDrop(TransferMode.MOVE);

                /* put a image on dragboard */
                ClipboardContent content = new ClipboardContent();

                Image sourceImage = avatarIv.getImage();
                content.putImage(sourceImage);
                db.setContent(content);
                avatarUrl = "/icons/avatar" + numOfAvatar + ".png";
                System.out.println(avatarUrl + "DRAGDETECTED");
                popOver.hide();
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

    @FXML
    protected void submitChanges(final ActionEvent event) {
        System.out.println(avatarUrl);
        if (dbAdaptor.updateAvatarUrl(MainHandler.username,avatarUrl)) {
            new ZoomIn(avatarImageBox).play();
        }

    }

    @FXML
    protected void changePass(final ActionEvent event) {

        String oldPassStr = oldPass.getText().trim();
        String newPassStr = newPass.getText().trim();
        String confirmNewPassStr = confirmNewPass
                .getText().trim();

        try {
            this.mainController.loadPersonalInfoScene();
        } catch (IOException err) {
            err.getMessage();
        }
        if (!newPassStr.equals(confirmNewPassStr)) {
            alertBuilder
                    .formEntryWarning("password fields",
                            "New password and confirmed"
                                    + "new password do not match!");
        }
    }
}
