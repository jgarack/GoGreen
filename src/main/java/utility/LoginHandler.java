package utility;

import exceptions.ServerStatusException;
import javafx.scene.control.Alert;

import javax.xml.bind.DatatypeConverter;
import java.io.BufferedReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class that handles the actions made by the user.
 * @see gui.LoginController
 * @author awjvanvugt
 */
public abstract class LoginHandler {
    /**
     * The domain on which the server is running.
     * {@value}
     */
    private static final String DOMAIN = "http://localhost:8080";
    /**
     * The folder that contains the log files for this class.
     * {@value}
     */
    private static final String LOGFOLDER = null;

    /**
     * The HttpRequestHandler used for this class.
     */
    private static final HttpRequestHandler HTTP_HANDLER =
            new HttpRequestHandler(DOMAIN);

    /**
     * Sends a registration request to the server with the input username and
     * password combination. Encrypts the password.
     * @param username The username for the account to register.
     * @param pass The password for the acccount to register.
     * @param confirmPass The password to be confirmed.
     * @return true iff registered
     */
    public static boolean registerSubmit(final String username,
                                      final String pass,
                                         final String confirmPass) {
        if (checkForm(username, pass, confirmPass)) {
            try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                String md5Pass = DatatypeConverter.printHexBinary(
                        md5.digest(pass.getBytes())).toUpperCase();
                BufferedReader httpBody = HTTP_HANDLER.reqPost("/register",
                        new AccountMessage(username, md5Pass));
                String contentText = HTTP_HANDLER.resLog(
                        httpBody, LOGFOLDER + "register_response");
                Alert displayResponse = new Alert(Alert.AlertType.CONFIRMATION);
                displayResponse.setTitle("Registration complete");
                displayResponse.setContentText("You can now log in.\n"
                        + contentText);
                displayResponse.showAndWait();
                return true;
            } catch (NoSuchAlgorithmException md5Error) {
                encryptionExceptionHandler(md5Error);
                return false;
            } catch (ServerStatusException e) {
                displayException(e);
                return false;
            } catch (IOException e) {
                displayException(e);
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Sends a login request to the server with the input username and password
     * combination. Encrypts the password.
     * @param username The username.
     * @param pass The password.
     * @return true iff logged in
     */
    public static boolean loginSubmit(final String username,
                                      final String pass) {
        if (checkForm(username, pass)) {
            try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                String md5Pass = DatatypeConverter.printHexBinary(
                        md5.digest(pass.getBytes())).toUpperCase();
                BufferedReader httpBody = HTTP_HANDLER.reqPost("/login",
                        new AccountMessage(username, md5Pass));
                String contentText = HTTP_HANDLER.resLog(
                        httpBody, LOGFOLDER + "login_response");
                Alert displayResponse = new Alert(Alert.AlertType.CONFIRMATION);
                displayResponse.setTitle("Logged in");
                displayResponse.setContentText(contentText);
                displayResponse.showAndWait();
                return true;
            } catch (NoSuchAlgorithmException md5Error) {
                encryptionExceptionHandler(md5Error);
                return false;
            } catch (ServerStatusException e) {
                displayException(e);
                return false;
            } catch (IOException e) {
                displayException(e);
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Checks if the input received is in the correct format (not null,
     * whitespaces are omitted).
     * @param userFieldEntry The received input for the username.
     * @param passFieldEntry The received input for the password.
     * @return true iff the input is in the correct format.
     */
    private static boolean checkForm(final String userFieldEntry,
                                     final String passFieldEntry) {
        if (userFieldEntry.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Username not filled");
            alert.setContentText("You need to fill in your username");
            alert.showAndWait();
            return false;
        } else if (passFieldEntry.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Password not filled");
            alert.setContentText("You need to fill in your password");

            alert.showAndWait();
            return false;
        }
        return true;
    }

    /**
     * Extends the check to be applied
     * for the registration of the user.
     * @param userFieldEntry The received input for the username.
     * @param passFieldEntry The received input for the password.
     * @param confirmPassFieldEntry The received
     *                              input for the confirmed password.
     * @return true iff the input is in the correct format.
     */
    private static boolean checkForm(final String userFieldEntry,
                                     final String passFieldEntry,
                                     final String confirmPassFieldEntry) {
        if (checkForm(userFieldEntry, passFieldEntry)) {
            if (confirmPassFieldEntry.equals(passFieldEntry)) {
                return true;
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Password do not match!");
                alert.setContentText("You need to type in matching passwords!");
                alert.showAndWait();
            }
        }
        return false;
    }

    /**
     * Displays an error message for a thrown exception.
     * @param e the thrown exception to display
     */
    private static void encryptionExceptionHandler(
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
    private static void displayException(final Exception e) {
        Alert statusCodeError = new Alert(Alert.AlertType.ERROR);
        statusCodeError.setTitle(e.getMessage());
        statusCodeError.setContentText("See terminal for stacktrace.");
        e.printStackTrace();
        statusCodeError.showAndWait();
    }
}
