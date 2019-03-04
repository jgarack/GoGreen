package gui;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
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
        redirectToView(event,"register");
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
        redirectToView(event,"main");
    }

    /**
     * Redirects the user from login screen
     * to main screen by setting up a new scene.
     * @param event The event fired
     * when the user clicks the button from the login button.
     */
    private void redirectToView(final ActionEvent event,String view) {
        Stage stage = (Stage) ((Node) event.getSource())
                .getScene().getWindow();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass()
                    .getResource("/" + view + "View.fxml"));
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
