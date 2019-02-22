package gui;

import client.AccountMessage;
import client.HttpRequestHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.net.HttpURLConnection;

import javafx.event.ActionEvent;

import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private Text actionTarget;
    @FXML
    private TextField username;
    @FXML
    private PasswordField pass;
    @FXML
    private Button submitLogin;

    private String domain = "http://localhost:8080";

    @FXML
    protected void initialize(){

    }

    /** Activated when the User presses the submit button, sends a POST request to the server with login credentials
     * @author Lassie, Jonathan, awjvanvugt
     * @param event the button press
     * @throws Exception if the requesthandler does
     */
    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) throws Exception{
        if(username.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Username not filled");
            alert.setContentText("You need to fill in your username");
            alert.showAndWait();
        }
        else if(pass.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Password not filled");
            alert.setContentText("You need to fill in your password");
            alert.showAndWait();
        }
        else {
            AccountMessage loginMess = new AccountMessage(username.getText(),pass.getText());
            BufferedReader response = HttpRequestHandler.reqPost(domain+"/login", loginMess);

        }

    }



}
