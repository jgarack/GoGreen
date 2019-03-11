package gui;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Rating;

public class aboutController {
    @FXML
    private Rating ratingBar;

    @FXML
    public void initialize(){
        ratingBar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ratingBar.setUpdateOnHover(false);
            }
        });
    }
}
