package utility;

import animatefx.animation.ZoomIn;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;

import java.io.IOException;

public class MainHandler {
    /**
     * Generates dialog alert box with the message provided.
     * @param msg the message that should alert the user
     */
    public static void generateAlert(final String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Wrongly filled in info");
        alert.setContentText(msg);

        alert.showAndWait();
    }

    /**
     * Tries to parse integer.
     *
     * @param value String that is to be parsed.
     * @return true iff the value provided is actual representation of integer.
     */
    public static boolean tryParseInt(final String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
