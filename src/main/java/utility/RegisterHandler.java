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
     */
    private String domain;
    /**
     * The folder that contains the log files for this class.
     * {@value}
     */
    private static final String LOGFOLDER = null;

    /**
     * The HttpRequestHandler used for this class.
     */
    public HttpRequestHandler httpHandler;
    /**
     * The builder used to build alerts for this handler.
     */
    public AlertBuilder alert;

    /**
     * Constructor.
     * @param host the domain to operate on
     */
    public RegisterHandler(String host) {
        domain = host;
        alert = new AlertBuilder();
        httpHandler = new HttpRequestHandler(domain);
    }

    /**
     * Sends a registration request to the server with the input username and
     * password combination. Encrypts the password.
     * @param username The username for the account to register.
     * @param pass The password for the acccount to register.
     * @param confirmPass The password to be confirmed.
     * @return true iff registered
     */
    public boolean registerSubmit(final String username,
                                         final String pass,
                                         final String confirmPass) {
        if (checkForm(username, pass, confirmPass)) {
            try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                String md5Pass = DatatypeConverter.printHexBinary(
                        md5.digest(pass.getBytes()));
                httpHandler.reqPost("/register",
                        new AccountMessage(username, md5Pass));
                alert.formNotificationPane("You have registered successfully!");
                return true;
            } catch (NoSuchAlgorithmException md5Error) {
                alert.encryptionExceptionHandler(md5Error);
                return false;
            } catch (Exception e) {
                alert.displayException(e);
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
    private boolean checkForm(final String userFieldEntry,
                                     final String passFieldEntry,
                                     final String confirmPassFieldEntry) {
        final String empty = "field was empty";
        if(userFieldEntry.isEmpty()) {
            alert.formEntryWarning("username", empty);
            return false;
        }
        if(passFieldEntry.isEmpty()) {
            alert.formEntryWarning("password", empty);
            return false;
        }
        if(confirmPassFieldEntry.isEmpty()) {
            alert.formEntryWarning("confirm password", empty);
            return false;
        }
        if(!(passFieldEntry.equals(confirmPassFieldEntry))) {
            alert.formEntryWarning("password fields",
                    "passwords don't match");
            return false;
        }
        return true;
    }
}
