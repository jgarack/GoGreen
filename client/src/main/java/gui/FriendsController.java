package gui;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.controlsfx.control.PopOver;
import utility.DbAdaptor;
import utility.MainHandler;
import utility.User;


/**
 * Controller for the friends page.
 */
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
     * Dbadaptor for getting users
     */
    private DbAdaptor dbAdaptor = new DbAdaptor();

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
        usernameCol
                .setCellValueFactory(new PropertyValueFactory<>("username"));
        TableColumn totalScoreCol = new TableColumn("Score");
        totalScoreCol
                .setCellValueFactory(new PropertyValueFactory<>("totalScore"));
        friendsTable
                .getColumns()
                .addAll(usernameCol, totalScoreCol);

        friendsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);



        friendsTable.setRowFactory(tv -> {
            TableRow<User> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if ( event.getButton() == MouseButton.SECONDARY) {
                    attachAddFriendPopOver(row);
                }
            });
            return row;
        });


        //Added users to style.
        friendsTable.getItems().add(new User("Added" , 299));
        friendsTable.getItems().add(new User("Some" , 330));
        friendsTable.getItems().add(new User("Users" , 360));
        friendsTable.getItems().add(new User("To" , 320));
        friendsTable.getItems().add(new User("Check" , 340));
        friendsTable.getItems().add(new User("The" , 310));
        friendsTable.getItems().add(new User("Looks" , 350));
        friendsTable.getItems().add(new User("Out" , 300));

    }

    private void attachAddFriendPopOver(TableRow<User> row) {
        Button addFriendBtn = new Button();
        addFriendBtn.setText("Add friend");
        addFriendBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String sender = MainHandler.username;
                String recipient = row.getTableView().getItems().get(0).getUsername();
                System.out.println(sender + " -> " + recipient);
            }
        });
        VBox addFriendBox = new VBox();
        addFriendBox.getChildren().add(addFriendBtn);
        PopOver popOver = new PopOver(addFriendBox);
        popOver.show(row);
    }

    /**
     * Seaches with the given value.
     */
    @FXML
    protected void submitSearch() {
        String username = searchBar.getText().trim();
        System.out.println(username);
        friendsTable.getItems().clear();
        User searchedUser = dbAdaptor.getUser(username);
        friendsTable.getItems()
                .add(new User(searchedUser.getUsername(),
                        searchedUser.getTotalScore()));


    }
}
