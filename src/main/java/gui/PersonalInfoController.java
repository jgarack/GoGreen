package gui;

import animatefx.animation.BounceIn;
import animatefx.animation.ZoomIn;
import exceptions.ServerStatusException;
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
import utility.HttpRequestHandler;
import utility.LoginHandler;
import utility.MainHandler;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

/**
 * Controller for the personal info scene.
 */
public class PersonalInfoController {
    /**
     * Dimensions for the scene.
     */
    private static final int DIMENSIONS = 120;
    /**
     * Zero.
     */
    private static final int ZERO = 0;
    /**
     * One.
     */
    private static final int ONE = 1;
    /**
     * Four.
     */
    private static final int FOUR = 4;
    /**
     * Eight.
     */
    private static final int EIGHT = 8;

    /**
     * Localhost domain.
     * {@value}
     */
    private static final String LOCALHOST = "https://go-green-db.herokuapp.com";

    /**
     * The parent controller.
     */
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

    /**
     * Bound to the dbadaptor class.
     */
    private DbAdaptor dbAdaptor = new DbAdaptor();

    /**
     * The profile picture of the logged in user.
     */
    private Image img = new Image(dbAdaptor.getUser(MainHandler.username)
            .getAvatarUrl());


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

    /**
     * Sets the parent controller field.
     * @param controller the parent controller to assign.
     */
    public void setMainController(final MainController controller) {
        this.mainController = controller;
    }

    /**
     * Sets up the avatar of the user.
     */
    private void setUpAvatar() {
        System.out.println(dbAdaptor.getUser(MainHandler.username)
                .getAvatarUrl());
        avatarImageBox.setMaxSize(DIMENSIONS, DIMENSIONS);
        avatarImageBox.setPrefSize(DIMENSIONS, DIMENSIONS);
        avatarImageBox.setMinSize(DIMENSIONS, DIMENSIONS);
        avatarImageView.setPreserveRatio(false);
        avatarImageView.fitWidthProperty().bind(avatarImageBox.widthProperty());
        avatarImageView.fitHeightProperty().bind(avatarImageBox
                .heightProperty());
        avatarImageView.setImage(img);
        avatarImageView.setVisible(true);
        System.out.println(avatarImageView.getImage());
    }

    /**
     * Sets up the drag and drop property.
     * @param avatarBox the box where the drop is delivered.
     */
    private void setupDragAndDropTarget(final HBox avatarBox) {
        grid.setOnDragEntered(new EventHandler<DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
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
        avatarBox.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                Dragboard db = event.getDragboard();

                if (db.hasImage()) {
                    event.acceptTransferModes(TransferMode.MOVE);
                }
                event.consume();
            }
        });
        avatarBox.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasImage()) {
                    if (db.hasUrl() &&  db.getUrl().contains("imgur.com")
                            &&  db.getUrl().contains("https")) {

                        avatarUrl = db.getUrl();
                        avatarImageView.setImage(db.getImage());
                        new BounceIn(avatarImageBox).play();
                        event.setDropCompleted(true);
                    } else if (!db.hasUrl() && avatarUrl.startsWith("/icons/avatar")) {
                        avatarImageView.setImage(db.getImage());
                        new BounceIn(avatarImageBox).play();
                        event.setDropCompleted(true);
                    } else {
                        alertBuilder.showAlertNotification(
                                "Avatar update failed."
                                + "\nTry dragging another image.");
                        event.setDropCompleted(false);
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

        for (int i = ZERO; i < EIGHT; i++) {
            if (i < FOUR) {
                gridPane.add(createAvatar(i + ONE), i, ZERO);
            } else {
                gridPane.add(createAvatar(i + ONE), i % FOUR, ONE);
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
    private Button createAvatar(final int numOfAvatar) {
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
            public void handle(final MouseEvent event) {

                /* allow any transfer mode */
                Dragboard db = avatar.startDragAndDrop(TransferMode.MOVE);

                /* put a image on dragboard */
                ClipboardContent content = new ClipboardContent();

                Image sourceImage = avatarIv.getImage();
                content.putImage(sourceImage);
                db.setContent(content);
                avatarUrl = "/icons/avatar" + numOfAvatar + ".png";
                popOver.hide();
                event.consume();
            }
        });
        avatar.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                avatar.setCursor(Cursor.OPEN_HAND);
            }
        });
        return avatar;
    }

    /**
     * Submits new avatar.
     * @param event event that triggers the method.
     */
    @FXML
    protected void submitChanges(final ActionEvent event) {
        System.out.println(avatarUrl);
        if (dbAdaptor.updateAvatarUrl(MainHandler.username, avatarUrl)) {
            new ZoomIn(avatarImageBox).play();
        }

    }

    /**
     * Submits new password.
     * @param event event that triggers the method.
     */
    @FXML
    protected void changePass(final ActionEvent event) {

        String oldPassStr = oldPass.getText().trim();
        String newPassStr = newPass.getText().trim();
        String confirmNewPassStr = confirmNewPass
                .getText().trim();

        if (newPassStr.equals(confirmNewPassStr) && new LoginHandler(LOCALHOST)
                .loginSubmit(MainHandler.username, oldPassStr)) {
            try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                newPassStr = DatatypeConverter.printHexBinary(
                        md5.digest(newPassStr.getBytes()));
            } catch (NoSuchAlgorithmException e) {
                alertBuilder.displayException(e);
            }
            try {
                new HttpRequestHandler(LOCALHOST).reqPost("/changepass",
                        new String[]{MainHandler.username, newPassStr});
            } catch (ServerStatusException | IOException exception) {
                alertBuilder.displayException(exception);
            }
        } else if (!newPassStr.equals(confirmNewPassStr)) {
            alertBuilder.showAlert("Passwords do not match",
                    "The new password do not match the confirmed password.");
        }
    }
}
