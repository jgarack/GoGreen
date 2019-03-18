package utility;

import gui.AlertBuilder;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class that handles the actions made by the user.
 * @see gui.LoginController
 * @author awjvanvugt
 */
public final class LoginHandler {
    /**
     * The domain on which the server is running.
     * {@value}
     */
    private String domain;
    /**
     * The folder that contains the log files for this class.
     * {@value}
     */
    private String logfolder = null;

    /**
     * The HttpRequestHandler used for this class.
     */
    public HttpRequestHandler httpHandler;

    /**
     * The builder used to build alerts for this handler.
     */
    public AlertBuilder alertBuilder;

    /**
     * Default constructor.
     * @param host The provided domain.
     */
    public LoginHandler(final String host) {
        domain = host;
        alertBuilder = new AlertBuilder();
        httpHandler = new HttpRequestHandler(domain);
    }

    /**
     * Sends a login request to the server with the input username and password
     * combination. Encrypts the password.
     *
     * @param username The username.
     * @param pass     The password.
     * @return true iff logged in
     */
    public boolean loginSubmit(final String username,
                                      final String pass) {
        if (emptyFields(username, pass)) {
            try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                String md5Pass = DatatypeConverter.printHexBinary(
                        md5.digest(pass.getBytes()));
                httpHandler.reqPost("/login",
                        new AccountMessage(username, md5Pass));
                alertBuilder.formNotification("Logged in successfully!");
                return true;
            } catch (NoSuchAlgorithmException md5Error) {
                alertBuilder.encryptionExceptionHandler(md5Error);
                return false;
            } catch (Exception e) {
                alertBuilder.displayException(e);
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Checks if the input received is in the correct format (not null,
     * whitespaces are omitted).
     *
     * @param userFieldEntry The received input for the username.
     * @param passFieldEntry The received input for the password.
     * @return true iff the input is in the correct format.
     */

    private boolean emptyFields(final String userFieldEntry,
                                       final String passFieldEntry) {
        if (userFieldEntry.isEmpty()) {

            alertBuilder.formEntryWarning("Username",
                    "You need to fill in your username");
            return false;
        } else if (passFieldEntry.isEmpty()) {
            alertBuilder.formEntryWarning("Password",
                    "You need to fill in your password");

            return false;
        }
        return true;
    }

}