package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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


public class RegisterController {
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

    @FXML
    protected void handleRegisterButtonAction(final ActionEvent event){
        if(LoginHandler.registerSubmit(username.getText().trim(), pass.getText())){
            redirectToView(event);
        }
    }

    @FXML
    protected void handleBackButtonAction(final ActionEvent event){
        redirectToView(event);
    }

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
