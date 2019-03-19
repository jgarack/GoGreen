package gui;

import javafx.scene.control.Alert;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import java.security.NoSuchAlgorithmException;

/**
 * Class that builds different kinds of alerts.
 */
public class AlertBuilder {
    /**
     * Duration of the defaultNotification.
     */
    private final double durationNotification = 1.5;
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
        statusCodeError.setTitle("Access denied!");
        statusCodeError
                .setContentText("Invalid username and password combination!");
        e.printStackTrace();
        statusCodeError.showAndWait();
    }
    /**
     * Displays a warning message.
     * @param fieldName the field that was not filled in correctly.
     * @param description a description of what went wrong.
     */
    public void formEntryWarning(final String fieldName,
                                  final String description) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Illegal entry in field " + fieldName + ":");
        alert.setContentText(description);
        alert.showAndWait();
    }

    /**
     * Forms a notification popup with a message.
     * @param notificationMsg The message of the popup.
     * @return Returns a notfication pane that should popup.
     */
    public Notifications formNotification(final String notificationMsg) {

        return Notifications.create()
                .darkStyle()
                .text(notificationMsg)
                .hideAfter(Duration
                        .seconds(durationNotification));
    }

    /**
     * Shows an information notification.
     * @param notificationMsg The message to be shown
     */
    public void showInformationNotification(final String notificationMsg) {
        formNotification(notificationMsg).showInformation();
    }
}
