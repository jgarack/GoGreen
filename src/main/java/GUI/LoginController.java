package GUI;

import utility.HttpRequestHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import javafx.event.ActionEvent;

import javafx.scene.control.TextField;
import utility.AccountMessage;

import javax.xml.bind.DatatypeConverter;
import java.io.BufferedReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginController {
    private final String domain = "http://localhost:8080";
    private final String logfolder = "src/logs/login/";
    @FXML
    private Text actionTarget;
    @FXML
    private TextField username;
    @FXML
    private TextField pass;
    @FXML
    private Button submitLogin;

    @FXML
    protected void initialize(){

    }

    @FXML
    protected void handleRegisterButtonAction(ActionEvent event){
        if (checkForm()) {
            try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                String md5Pass = DatatypeConverter.printHexBinary(md5.digest(pass.getText().getBytes())).toUpperCase();
                BufferedReader httpBody = HttpRequestHandler.reqPost(domain + "/register", new AccountMessage(username.getText().trim(), md5Pass));
                String contentText = HttpRequestHandler.resLog(httpBody, logfolder + "register_response");
                Alert displayResponse = new Alert(Alert.AlertType.CONFIRMATION);
                displayResponse.setTitle("Registration complete");
                displayResponse.setContentText("You can now log in.");
                displayResponse.showAndWait();
            } catch (NoSuchAlgorithmException md5Error) {
                encryptionExceptionHandler(md5Error);
            } catch (Exception e) {
                //TODO cleanup exception throw
                displayStatusCodeError(e);
            }
        }
    }

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event){
        if (checkForm()) {
            try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                String md5Pass = DatatypeConverter.printHexBinary(md5.digest(pass.getText().getBytes())).toUpperCase();
                BufferedReader httpBody = HttpRequestHandler.reqPost(domain + "/login", new AccountMessage(username.getText().trim(), md5Pass));
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

    protected boolean checkForm(){
        if(username.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Username not filled");
            alert.setContentText("You need to fill in your username");
            alert.showAndWait();
            return false;
        }
        else if(pass.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Password not filled");
            alert.setContentText("You need to fill in your password");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    protected void encryptionExceptionHandler(Exception e){
        Alert encryptionError = new Alert(Alert.AlertType.ERROR);
        encryptionError.setTitle("Encryption failure:");
        encryptionError.setContentText("The client failed to encrypt your login credentials, and your login attempt was aborted." +
                "\nPlease try again and contact an administrator if this issue persists.\nException found:\n"+e.getMessage()+
                "\n"+e.fillInStackTrace());
    }

    protected void displayStatusCodeError(Exception e){
        Alert statusCodeError = new Alert(Alert.AlertType.ERROR);
        statusCodeError.setTitle(e.getMessage());
        statusCodeError.setContentText("See terminal for stacktrace.");
        statusCodeError.showAndWait();
    }



}
