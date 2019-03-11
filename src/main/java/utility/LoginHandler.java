package utility;

import exceptions.ServerStatusException;
import javafx.scene.control.Alert;
import gui.AlertBuilder;

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
     * The builder used to build alerts for this handler.
     */
    private static final AlertBuilder alert = new AlertBuilder();
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
                alert.encryptionExceptionHandler(md5Error);
                return false;
            } catch (ServerStatusException e) {
                alert.displayException(e);
                return false;
            } catch (IOException e) {
                alert.displayException(e);
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
        if (emptyFields(username, pass)) {
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
                alert.encryptionExceptionHandler(md5Error);
                return false;
            } catch (ServerStatusException e) {
                alert.displayException(e);
                return false;
            } catch (IOException e) {
                alert.displayException(e);
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
    private static boolean emptyFields(final String userFieldEntry,
                                     final String passFieldEntry) {
        if (userFieldEntry.isEmpty()) {
            alert.formEntryWarning("Username",
                    "You need to fill in your username");
            return false;
        } else if (passFieldEntry.isEmpty()) {
            alert.formEntryWarning("Password",
                    "You need to fill in your password");
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
        if (emptyFields(userFieldEntry, passFieldEntry)) {
            if (confirmPassFieldEntry.equals(passFieldEntry)) {
                return true;
            } else {
                alert.formEntryWarning("Password",
                        "Passwords do not match!");
            }
        }
        return false;
    }
}
