package gui;


import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import utility.User;

public class FriendsController {

    /**
     * Bound to the vertical box with added friends.
     */
    @FXML
    private GridPane friendsPane;

    /**
     * Bound to the area of pending friend requests.
     */
    @FXML
    private HBox menuFriends;

    /**
     * Bound to the search bar.
     */
    @FXML
    private TextField searchBar;
    /**
     * Table for listing friends.
     */
    @FXML
    private TableView friendsTable;


    /**
     * Should contain the list of friends of a user.
     */
    private int[] friends;
    /**
     * Upon initialization, it triggers.
     */
    @FXML
    public void initialize() {
        constructTableFriends();
    }

    /**
     * Constructs table for friends.
     */
    private void constructTableFriends() {

        TableColumn usernameCol = new TableColumn("Username");
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        TableColumn totalScoreCol = new TableColumn("Score");
        totalScoreCol.setCellValueFactory(new PropertyValueFactory<>("totalScore"));
        friendsTable.getColumns().addAll(usernameCol,totalScoreCol);

        friendsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);



        //Added users for to style.
        friendsTable.getItems().add(new User("Added" , 299));
        friendsTable.getItems().add(new User("Some" , 330));
        friendsTable.getItems().add(new User("Users" , 360));
        friendsTable.getItems().add(new User("To" , 320));
        friendsTable.getItems().add(new User("Check" , 340));
        friendsTable.getItems().add(new User("The" , 310));
        friendsTable.getItems().add(new User("Looks" , 350));
        friendsTable.getItems().add(new User("Out" , 300));

    }
}
