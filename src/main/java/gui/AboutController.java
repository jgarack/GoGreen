package gui;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Rating;

/**
 * Controller for the about page.
 */
public class AboutController {
    /**
     * Bound to the rating bar in the FXML file.
     */
    @FXML
    private Rating ratingBar;

    /**
     * Method upon initialization.
     */
    @FXML
    public void initialize() {
        ratingBar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                ratingBar.setUpdateOnHover(false);
            }
        });
    }
}
