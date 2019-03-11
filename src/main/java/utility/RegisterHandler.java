package utility;

import exceptions.ServerStatusException;
import javafx.scene.control.Alert;
import utility.AccountMessage;
import utility.HttpRequestHandler;
import utility.LoginHandler;

import javax.xml.bind.DatatypeConverter;
import java.io.BufferedReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RegisterHandler {
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
                displayResponse.close();
                return true;
            } catch (NoSuchAlgorithmException md5Error) {
                LoginHandler.encryptionExceptionHandler(md5Error);
                return false;
            } catch (ServerStatusException e) {
                LoginHandler.displayException(e);
                return false;
            } catch (IOException e) {
                LoginHandler.displayException(e);
                return false;
            }
        } else {
            return false;
        }
    }
    private static boolean checkForm(final String userFieldEntry,
                                     final String passFieldEntry,
                                     final String confirmPassFieldEntry) {
        if (LoginHandler.checkForm(userFieldEntry, passFieldEntry)) {
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
}
