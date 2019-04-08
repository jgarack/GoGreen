package gui;

import animatefx.animation.Pulse;
import animatefx.animation.ZoomInLeft;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import utility.LoginHandler;
import utility.MainHandler;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Controller for the login window.
 */
public final class LoginController {
    /**
     * The WIDTH of the stage.
     */
    private static final int WIDTH = 800;
    /**
     * The HEIGHT of the stage.
     */
    private static final int HEIGHT = 600;
    /**
     * The root of the FXML page.
     */
    @FXML
    private GridPane grid;
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
     * LoginHandler used for handling login requests.
     */
    private LoginHandler handler = new LoginHandler("https://go-green-db.herokuapp.com");


    /**
     * Initialize method.
     */
    @FXML
    protected void initialize() {

//        this.cyclingBackground();

    }


    /**
     * Triggered when the user clicks "Register".
     *
     * @param event The event fired when the user clicks the button.
     * @see utility.RegisterHandler#registerSubmit
     */
    @FXML
    protected void handleRegisterButtonAction(final ActionEvent event) {
        redirectToView(event, "register");
    }

    /**
     * Triggered when the user clicks "Sign in".
     *
     * @param event The event fired when the user clicks the button.
     * @see utility.LoginHandler#loginSubmit(String, String)
     */
    @FXML
    protected void handleSubmitButtonAction(final ActionEvent event) {
        if (handler.loginSubmit(username.getText().trim(),
                pass.getText())) {
            redirectToView(event, "main");
        }
    }

    /**
     * Redirects the user from login screen
     * to main screen by setting up a new scene.
     *
     * @param event The event fired
     * @param view  The view to where the user should be redirected.
     *              When the user clicks the button from the login button.
     */
    private void redirectToView(final ActionEvent event, final String view) {
        Stage stage = (Stage) ((Node) event.getSource())
                .getScene().getWindow();
        try {

            System.out.println(1);
            MainHandler.username = username.getText().trim();
            System.out.println(MainHandler.username);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                    .getResource("/fxml/" + view + "View.fxml"));
            Parent root = fxmlLoader.load();


            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();


            if (view.equals("register")) {
                new Pulse(root).play();
            } else {
                MainController controller = fxmlLoader.getController();
                controller
                        .setGreetingsText("Greetings, " + username.getText()
                                .trim());
                new ZoomInLeft(root).play();
            }
            stage.setX(bounds.getMinX());
            stage.setY(bounds.getMinY());
            stage.setHeight(bounds.getHeight());
            stage.setWidth(bounds.getWidth());
            Scene scene = new Scene(root, WIDTH, HEIGHT);
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }

    }

    private void cyclingBackground() {

        for (int i = 1; i <= 5; i++) {

            if (i == 1) {
                grid.setStyle("-fx-background-image: url('/icons/backgroundPhase1.png')");
                System.out.println(1);
            } else if (i == 2) {
                grid.setStyle("-fx-background-image: url('/icons/backgroundPhase2.png')");
                System.out.println(2);
            } else if (i == 3) {
                grid.setStyle("-fx-background-image: url('/icons/backgroundPhase3.png')");
                System.out.println(3);
            } else if (i == 4) {
                grid.setStyle("-fx-background-image: url('/icons/backgroundPhase4.png')");
                System.out.println(4);
            } else {
                grid.setStyle("-fx-background-image: url('/icons/backgroundPhase5.png')");
                System.out.println(5);
                i = 0;
            }

            try {
                TimeUnit.SECONDS.sleep(1);
            }
            catch (InterruptedException ex) {
            }
        }

    }

}



//            BackgroundImage phases;
//            phases = new BackgroundImage(
//                    new Image("file:icons/backgroundPhase5.png", 100.0, 100.0, true, true),
//                    BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
//                    BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
//            grid.setStyle("-fx-background-image: url('/icons/backgroundPhase5.png')");
////            grid.setBackground(new Background(phases));
//
