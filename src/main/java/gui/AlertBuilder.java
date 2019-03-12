package gui;

import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.NotificationPane;
import org.controlsfx.control.Notifications;

import java.security.NoSuchAlgorithmException;

/**
 * Class that builds different kinds of alerts.
 */
public class AlertBuilder {
    /**
     * Default constructor.
     */
    public AlertBuilder() {
    }
    /**
     * Displays an error message for a thrown exception.
     * @param e the thrown exception to display
     */
    public void encryptionExceptionHandler(
            final NoSuchAlgorithmException e) {
        Alert encryptionError = new Alert(Alert.AlertType.ERROR);
        encryptionError.setTitle("Encryption failure:");
        encryptionError.setContentText("The client failed to encrypt your "
                + "login credentials, and your login attempt was aborted."
                + "\nPlease try again and contact an administrator if this"
                + "issue persists.\nException found:\n" + e.getMessage()
                + "\n see terminal for stacktrace");
        e.printStackTrace();
        encryptionError.showAndWait();
    }

    /**
     * Displays an error message for a thrown exception.
     * @param e the thrown exception to display
     */
    public void displayException(final Exception e) {
        Alert statusCodeError = new Alert(Alert.AlertType.ERROR);
        statusCodeError.setTitle(e.getMessage());
        statusCodeError.setContentText("See terminal for stacktrace.");
        e.printStackTrace();
        statusCodeError.showAndWait();
    }
    /**
     * Displays a warning message.
     * @param fieldName the field that was not filled in correctly.
     * @param description a description of what went wrong.
     * @return returns an alert with the message.
     */
    public Alert formEntryWarning(final String fieldName,
                                  final String description) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Illegal entry in field " + fieldName + ":");
        alert.setContentText(description);
        return alert;
    }

    /**
     * Forms a notification popup with a message.
     * @param notificationMsg The message of the popup.
     * @return Returns a notfication pane that should popup.
     */
    public final Notifications formNotificationPane(final String notificationMsg) {

        return Notifications.create()
                .darkStyle()
                .text(notificationMsg)
                .hideAfter(Duration.seconds(1.5)); // sets node to display
    }
}
