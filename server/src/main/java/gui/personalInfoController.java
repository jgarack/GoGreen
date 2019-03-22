package gui;


import animatefx.animation.Pulse;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import org.controlsfx.control.PopOver;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.GlyphFontRegistry;


public class personalInfoController {

    @FXML
    private PopOver popOver;

    @FXML
    private Button changeAvatar;

    @FXML
    private ImageView avatarImageView;

    @FXML
    private Label infoChangeAvatarLabel;

    @FXML
    private InformationBuilder informationBuilder = new InformationBuilder();

    @FXML
    private void initialize(){
        informationBuilder.addInformationIconToSearchBox(infoChangeAvatarLabel);
    }



    @FXML
    public void changeAvatar(final ActionEvent event) {
        GridPane gridPane = new GridPane();

        for(int i = 0 ; i < 8 ; i++) {
            if(i<4) gridPane.add(creataAvatar(i+1), i, 0);
            else gridPane.add(creataAvatar(i+1), i % 4, 1 );
        }
        popOver = new PopOver(gridPane);
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
                popOver.hide();
            }
        });
        return avatar;
    }


}
