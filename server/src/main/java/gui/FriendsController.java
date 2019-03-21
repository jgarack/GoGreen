package gui;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Group;
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

import java.util.ArrayList;
import java.util.List;


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
    private ArrayList<String> friends;
    /**
     * The builder used to build alerts for this handler.
     */
    private AlertBuilder alertBuilder;



    /**
     * Upon initialization, it triggers.
     */
    @FXML
    public void initialize() {
        friends = (ArrayList<String>) dbAdaptor.getRequest(MainHandler.username);
        constructPendingListView();
        constructTableFriends();
    }

    /**
     * Constructs the list view
     * with pending requests.
     */
    private void constructPendingListView() {
        if(friends.isEmpty()){

        } else {
            Label pendingReqTitle = new Label("Pending Requests:");
            pendingReqTitle.setId("pendingReqTitle");
            ListView listOfPendingReq = new ListView();
            for (String friend : friends) {
                HBox currFriend = new HBox();
                Label sender = new Label(friend + "sent you a request!");

                Button acceptBtn = new Button("Accept");
                acceptBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        dbAdaptor.considerRequest(friend,MainHandler.username,true);
                    }
                });


                Button declineBtn = new Button("Block user");
                declineBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        dbAdaptor.considerRequest(friend,MainHandler.username,true);
                    }
                });

                //sender.setAlignment(Pos.CENTER_LEFT);

                currFriend.getChildren().addAll(sender,acceptBtn,declineBtn);
                currFriend.setAlignment(Pos.CENTER);
                currFriend.setSpacing(30);

                listOfPendingReq.getItems().add(currFriend);

            }
            friendsPane.add(pendingReqTitle,0,0);
            friendsPane.add(listOfPendingReq,0,2);
        }
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

        if (friends.isEmpty()){

        } else {
            for(String friend : friends){
                friendsTable.getItems().add(new User(friend,dbAdaptor.getTotalScore(friend)));
            }
        }

        //Added users to style.
        friendsTable.getItems().add(new User("Added" , 299));
        friendsTable.getItems().add(new User("Some" , 330));
        friendsTable.getItems().add(new User("Users" , 360));



    }

    private void attachAddFriendPopOver(TableRow<User> row) {
        Button addFriendBtn = new Button();
        addFriendBtn.setText("Add friend");
        addFriendBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String sender = MainHandler.username;
                String recipient = row.getTableView().getItems().get(0).getUsername();
                dbAdaptor.sendFriendReq(sender,recipient);
                //TODO: make sendFriendReq boolean so that notifications are adequate
                alertBuilder.showInformationNotification("Friend request sent!");
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
