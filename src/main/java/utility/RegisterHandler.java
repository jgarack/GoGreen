package utility;

import exceptions.ServerStatusException;
import gui.AlertBuilder;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

/**
 * Class for handling register forms.
 */
public class RegisterHandler {

    /**
     * The folder that contains the log files for this class.
     * {@value}
     */
    private static final String LOGFOLDER = null;

    /**
     * The builder used to build alerts for this handler.
     */
    public AlertBuilder alertBuilder;

    /**
     * The HttpRequestHandler used for this class.
     */
    public HttpRequestHandler httpHandler;

    /**
     * The domain on which the server is running.
     */
    private String domain;

    /**
     * Constructor.
     * @param host the domain to operate on
     */
    public RegisterHandler(final String host) {
        domain = host;
        alertBuilder = new AlertBuilder();
        httpHandler = new HttpRequestHandler(domain);
    }

    /**
     * Sends a registration request to the server with the input username and
     * password combination. Encrypts the password.
     * @param username The username for the account to register.
     * @param pass The password for the acccount to register.
     * @param confirmPass The password to be confirmed.
     * @param secretQuestion Secret question of the user.
     * @param secretAnswer Answer to the secret question.
     * @return true iff registered
     */
    public boolean registerSubmit(final String username,
                                         final String pass,
                                         final String confirmPass,
                                         final String secretQuestion,
                                         final String secretAnswer) {
        if (checkForm(username, pass, confirmPass,
                secretQuestion, secretAnswer)) {
            try {

                MessageDigest md5 = MessageDigest.getInstance("MD5");
                String md5Pass = DatatypeConverter.printHexBinary(
                        md5.digest(pass.getBytes()));
                RegisterCredentials registerCredentials =
                        new RegisterCredentials(username, md5Pass,
                                secretQuestion, secretAnswer);

                httpHandler.reqPost("/register",
                        registerCredentials);
                System.out.println("reqpost did not throw exception");
                alertBuilder
                        .showInformationNotification(
                                "You have registered successfully!");
                return true;
            } catch (NoSuchAlgorithmException md5Error) {
                alertBuilder.encryptionExceptionHandler(md5Error);
                return false;
            } catch (ServerStatusException e) {
                int statusCode = e.getHttpStatusCode();
                if (statusCode == HttpURLConnection.HTTP_CONFLICT) {
                    alertBuilder.showAlert("Username already taken!",
                            "This username is already taken."
                                    + " Please choose another one.");
                }
                return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    /**
     * Checks whether the filled form
     * has been filled with valid input.
     * @param userFieldEntry The received input for the username.
     * @param passFieldEntry The received input for the password.
     * @param confirmPassFieldEntry The received
     *                              input for the confirmed password.
     * @param secretQuestion The received secret question.
     * @param secretAnswer The received secret answer.
     * @return true iff the input is in the correct format.
     */
    private boolean checkForm(final String userFieldEntry,
                                     final String passFieldEntry,
                                     final String confirmPassFieldEntry,
                                     final String secretQuestion,
                                     final String secretAnswer) {
        if (emptyFields(userFieldEntry, passFieldEntry,
                secretQuestion, secretAnswer)) {
            if (confirmPassFieldEntry.equals(passFieldEntry)) {
                return true;
            } else {

                alertBuilder
                        .formEntryWarning("Password/Confirm Password",
                                "Passwords do not match!");
                return false;
            }
        }
        return false;
    }

    /**
     * Checks the given values whether they are empty.
     * @param userFieldEntry Value for username.
     * @param passFieldEntry Value for password.
     * @param secretQ Value for secret question.
     * @param secretAnswer Value for secret answer.
     * @return true iff none of the fields is empty.
     */
    private boolean emptyFields(final String userFieldEntry,
                                      final String passFieldEntry,
                                      final String secretQ,
                                      final String secretAnswer) {
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
