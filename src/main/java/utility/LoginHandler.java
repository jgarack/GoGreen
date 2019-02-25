package utility;

import javafx.scene.control.Alert;

import javax.xml.bind.DatatypeConverter;
import java.io.BufferedReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class LoginHandler {
    private static String domain = "http://localhost:8080";
    private static String logfolder = null;

    public static void registerSubmit(String username, String pass) {
        if (checkForm(username, pass)) {
            try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                String md5Pass = DatatypeConverter.printHexBinary(md5.digest(pass.getBytes())).toUpperCase();
                BufferedReader httpBody = HttpRequestHandler.reqPost(domain + "/register", new AccountMessage(username, md5Pass));
                String contentText = HttpRequestHandler.resLog(httpBody, logfolder + "register_response");
                Alert displayResponse = new Alert(Alert.AlertType.CONFIRMATION);
                displayResponse.setTitle("Registration complete");
                displayResponse.setContentText("You can now log in.\n" + contentText);
                displayResponse.showAndWait();
            } catch (NoSuchAlgorithmException md5Error) {
                encryptionExceptionHandler(md5Error);
            } catch (Exception e) {
                //TODO cleanup exception throw
                displayStatusCodeError(e);
            }
        }
    }

    public static void loginSubmit(String username, String pass) {
        if (checkForm(username, pass)) {
            try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                String md5Pass = DatatypeConverter.printHexBinary(md5.digest(pass.getBytes())).toUpperCase();
                BufferedReader httpBody = HttpRequestHandler.reqPost(domain + "/login", new AccountMessage(username, md5Pass));
                String contentText = HttpRequestHandler.resLog(httpBody, logfolder + "login_response");
                Alert displayResponse = new Alert(Alert.AlertType.CONFIRMATION);
                displayResponse.setTitle("Logged in");
                displayResponse.setContentText(contentText);
                displayResponse.showAndWait();
            } catch (NoSuchAlgorithmException md5Error) {
                encryptionExceptionHandler(md5Error);
            } catch (Exception e) {
                //TODO cleanup exception throw
                displayStatusCodeError(e);
            }
        }
    }

    private static boolean checkForm(String userFieldEntry, String passFieldEntry) {
        if(userFieldEntry.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Username not filled");
            alert.setContentText("You need to fill in your username");
            alert.showAndWait();
            return false;
        }

        else if(passFieldEntry.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Password not filled");
            alert.setContentText("You need to fill in your password");

            alert.showAndWait();
            return false;
        }
        return true;
    }

    private static void encryptionExceptionHandler(Exception e) {
        Alert encryptionError = new Alert(Alert.AlertType.ERROR);
        encryptionError.setTitle("Encryption failure:");
        encryptionError.setContentText("The client failed to encrypt your login credentials, and your login attempt was aborted." +
                "\nPlease try again and contact an administrator if this issue persists.\nException found:\n"+e.getMessage()+
                "\n"+e.fillInStackTrace());
    }

    private static void displayStatusCodeError(Exception e) {
        Alert statusCodeError = new Alert(Alert.AlertType.ERROR);
        statusCodeError.setTitle(e.getMessage());
        statusCodeError.setContentText("See terminal for stacktrace.");
        statusCodeError.showAndWait();
    }
}
