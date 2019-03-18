package gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.controlsfx.control.textfield.TextFields;

public class FriendsController {

    @FXML
    private VBox friendsListBox;

    /**
     * Upon initialization, it triggers.
     */
    @FXML
    public void initialize(){
        TextField searchBar = TextFields.createClearableTextField();
        friendsListBox.getChildren().add(searchBar);
    }
}
