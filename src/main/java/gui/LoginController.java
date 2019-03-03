package gui;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utility.LoginHandler;
import java.io.IOException;

/**
 * Controller for the login window.
 */
public final class LoginController {
    /**
     * The width of the stage.
     */
    private final int width = 800;
    /**
     * The height of the stage.
     */
    private final int height = 600;
    /**
     * Bound to the text field where the user enters his username.
     */
    @FXML
    private TextField username;
    /**
     * Bound to the text field where the user enters his password.
     */
    @FXML
    private TextField pass;


    /**
     * Initialize method.
     */
    @FXML
    protected void initialize() {
    }



    /**
     * Triggered when the user clicks "Register".
     *
     * @param event The event fired when the user clicks the button.
     * @see utility.LoginHandler#registerSubmit(String, String)
     */
    @FXML
    protected void handleRegisterButtonAction(final ActionEvent event) {
        LoginHandler.registerSubmit(username.getText().trim(), pass.getText());
    }

    /**
     * Triggered when the user clicks "Sign in".
     *
     * @param event The event fired when the user clicks the button.
     * @see utility.LoginHandler#loginSubmit(String, String)
     */
    @FXML
    protected void handleSubmitButtonAction(final ActionEvent event) {
        LoginHandler.loginSubmit(username.getText().trim(),
                pass.getText());
        redirectToMain(event);
    }

    /**
     * Redirects the user from login screen
     * to main screen by setting up a new scene.
     * @param event The event fired
     * when the user clicks the button from the login button.
     */
    private void redirectToMain(final ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource())
                .getScene().getWindow();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass()
                    .getResource("/mainView.fxml"));
            Scene scene = new Scene(root, width, height);
            stage.setScene(scene);
            stage.setFullScreen(true);
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }

    }
}
