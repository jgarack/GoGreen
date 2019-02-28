package gui;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import utility.LoginHandler;
import features.VegetarianMeal;

/**
 * Controller for the login window.
 */
public final class LoginController {
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
    private TextField test;

    /**
     * Initialize method.
     */
    @FXML
    protected void initialize() { }

    /**
     * Triggered when the user clicks "Register".
     * @param event The event fired when the user clicks the button.
     * @see utility.LoginHandler#registerSubmit(String, String)
     */
    @FXML
    protected void handleRegisterButtonAction(final ActionEvent event) {
        LoginHandler.registerSubmit(username.getText().trim(), pass.getText());
    }

    /**
     * Triggered when the user clicks "Sign in".
     * @param event The event fired when the user clicks the button.
     * @see utility.LoginHandler#loginSubmit(String, String)
     */
    @FXML
    protected void handleSubmitButtonAction(final ActionEvent event) {
        LoginHandler.loginSubmit(username.getText().trim(), pass.getText());
        VegetarianMeal testMeal = new VegetarianMeal(test);
        System.out.println(testMeal.toString());
        testMeal.calculatePoints(event);
    }
}
