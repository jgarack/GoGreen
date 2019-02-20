package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import javafx.event.ActionEvent;

import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private Text actionTarget;
    @FXML
    private TextField username;
    @FXML
    private TextField pass;


    @FXML
    protected void handleSubmitButtonAction(ActionEvent event){
        if(username.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Username not filled");
            alert.setContentText("You need to fill in your username");
            alert.showAndWait();
        }
        else {
            String user = username.getText();
            actionTarget.setText("Welcome, " + user + "!");
        }

    }



}
