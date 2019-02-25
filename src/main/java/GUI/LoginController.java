package GUI;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import utility.LoginHandler;

public class LoginController {
    @FXML
    private TextField username;
    @FXML
    private TextField pass;


    @FXML
    protected void initialize() { }

    @FXML
    protected void handleRegisterButtonAction(ActionEvent event) {
        LoginHandler.registerSubmit(username.getText().trim(), pass.getText());
    }

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        LoginHandler.loginSubmit(username.getText().trim(), pass.getText());
    }
}