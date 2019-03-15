package utility;

import exceptions.ServerStatusException;
import gui.AlertBuilder;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class for handling register forms.
 */
public class RegisterHandler {
    /**
     * Private constructor just because.
     */
    private RegisterHandler() { }
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
    private static final AlertBuilder ALERT_BUILDER = new AlertBuilder();

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
                HTTP_HANDLER.reqPost("/register",
                        new AccountMessage(username, md5Pass));
                alert.formNotificationPane("You have registered successfully!");
                return true;
            } catch (NoSuchAlgorithmException md5Error) {
                ALERT_BUILDER.encryptionExceptionHandler(md5Error);
                return false;
            } catch (ServerStatusException e) {
                ALERT_BUILDER.displayException(e);
                return false;
            } catch (IOException e) {
                ALERT_BUILDER.displayException(e);
                return false;
            }
        } else {
            return false;
        }
    }
    /**
     * Checks whether the filled form
     * has been filled with valid input.
     * @param userFieldEntry The received input for the username.
     * @param passFieldEntry The received input for the password.
     * @param confirmPassFieldEntry The received
     *                              input for the confirmed password.
     * @return true iff the input is in the correct format.
     */
    private static boolean checkForm(final String userFieldEntry,
                                     final String passFieldEntry,
                                     final String confirmPassFieldEntry) {
        if (new LoginHandler(null).emptyFields(userFieldEntry, passFieldEntry)) {
            if (confirmPassFieldEntry.equals(passFieldEntry)) {
                return true;
            } else {
                AlertBuilder alertBuilder = new AlertBuilder();
                alertBuilder
                        .formEntryWarning("Password/Confirm Password",
                                "Passwords do not match!")
                        .showAndWait();
            }
        }
        return false;
    }
}
