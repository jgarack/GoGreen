package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.PopOver;


public class personalInfoController {

    @FXML
    private Button changeAvatar;

    @FXML
    private ImageView avatarImageView;

    @FXML
    public void changeAvatar(final ActionEvent event) {
        GridPane gridPane = new GridPane();

        for(int i = 0 ; i < 8 ; i++) {
            if(i<4) gridPane.add(creataAvatar(i+1), i, 0);
            else gridPane.add(creataAvatar(i+1), i % 4, 1 );
        }
        PopOver popOver = new PopOver(gridPane);
        popOver.show(changeAvatar);
    }


    private Button creataAvatar(int numOfAvatar) {
        Button avatar = new Button();
        ImageView avatarIv = new ImageView();
        Image avatarImage = new Image("/icons/avatar" + numOfAvatar + ".png");
        avatarIv.setImage(avatarImage);
        avatar.setGraphic(avatarIv);
        avatar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Button btn = (Button) event.getSource();
                ImageView imageView = (ImageView) btn.getGraphic();

                avatarImageView.setImage(imageView.getImage());
            }
        });
        return avatar;
    }


}
