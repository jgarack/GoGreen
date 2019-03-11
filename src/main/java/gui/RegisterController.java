package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import utility.LoginHandler;

import java.io.IOException;

/**
 * The controller for the Register page.
 */
public class

RegisterController {
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
     * Bound to the password field where the user enters his password.
     */
    @FXML
    private PasswordField pass;

    /**
     * Bound to the password field where the user confirms his password.
     */
    @FXML
    private PasswordField confirmPass;

    /**
     * Handles registration attempt based on the input.
     * @param event The event fired when the button is clicked.
     */
    @FXML
    protected void handleRegisterButtonAction(final ActionEvent event) {
        if (LoginHandler.registerSubmit(username.getText().trim(),
                pass.getText().trim(), confirmPass.getText().trim())) {
                redirectToView(event);
        }
    }

    /**
     * Redirects to the login screen when the button is clicked.
     * @param event The event fired when the button is clicked.
     */
    @FXML
    protected void handleBackButtonAction(final ActionEvent event) {
        redirectToView(event);
    }

    /**
     * Redirects to the login screen.
     * @param event The event that is fired from the previous method.
     */
    private void redirectToView(final ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource())
                .getScene().getWindow();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass()
                    .getResource("/loginView.fxml"));
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();

            stage.setX(bounds.getMinX());
            stage.setY(bounds.getMinY());
            stage.setWidth(bounds.getWidth());
            stage.setHeight(bounds.getHeight());
            Scene scene = new Scene(root, width, height);
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }

    }
}
