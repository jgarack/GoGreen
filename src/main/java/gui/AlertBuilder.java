package gui;

import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.controlsfx.control.NotificationPane;

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
     * @param stage The stage where it should pop up.
     * @return Returns a notfication pane that should popup.
     */
    final NotificationPane formNotificationPane(final String notificationMsg,
                                          final Stage stage) {
        Parent root = stage.getScene().getRoot();
        NotificationPane notificationPane = new NotificationPane(root);
        notificationPane.setText(notificationMsg);
        notificationPane.getStyleClass().add(NotificationPane.STYLE_CLASS_DARK);
        notificationPane.setShowFromTop(false);
        notificationPane.show();
        return notificationPane;
    }
}
