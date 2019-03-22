package gui;


import animatefx.animation.BounceInLeft;
import animatefx.animation.Pulse;
import animatefx.animation.SlideInRight;
import animatefx.animation.ZoomInRight;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.util.Duration;
import org.controlsfx.control.PopOver;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.GlyphFontRegistry;
import utility.DbAdaptor;
import utility.MainHandler;
import utility.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
     * Bound to the search box.
     */
    @FXML
    private Label searchInfoLabel;

    /**
     * Dbadaptor for getting users.
     */
    private DbAdaptor dbAdaptor = new DbAdaptor();

    /**
     * Should contain the list of friends of a user.
     */
    private ArrayList<String> friends;
    /**
     * Should contain list of pending requests.
     */
    private ArrayList<String> pendingRequests;
    /**
     * The builder used to build alerts for this handler.
     */
    private AlertBuilder alertBuilder = new AlertBuilder();

    private InformationBuilder informationBuilder = new InformationBuilder();



    /**
     * Upon initialization, it triggers.
     */
    @FXML
    public void initialize() {
        pendingRequests = (ArrayList<String>) dbAdaptor
                .getRequest(MainHandler.username);
        friends =  (ArrayList<String>)dbAdaptor.getFriends(MainHandler.username);

        informationBuilder
                .addInformationIconToSearchBox(searchInfoLabel,
                        "Search for a user by his username\nand do a right-click on his name\nto add him.");

        constructTableFriends();
        constructPendingListView();
    }

    /**
     * Constructs the list view
     * with pending requests.
     */
    private void constructPendingListView() {
        if (!pendingRequests.isEmpty()) {
            Label pendingReqTitle = new Label("Pending Requests");
            pendingReqTitle.setId("pendingReqTitle");
            ListView listOfPendingReq = new ListView();
            for (String friend : pendingRequests) {
                HBox currFriend = new HBox();
                Label sender = new Label(friend + " sent you a request!");

                Button acceptBtn = new Button("Accept");
                acceptBtn.setStyle("-fx-font-family: 'FontAwesome'");
                acceptBtn
                        .setGraphic(GlyphFontRegistry
                        .font("FontAwesome")
                        .create(FontAwesome.Glyph.CHECK_CIRCLE_ALT));
                acceptBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent event) {
                        dbAdaptor
                                .considerRequest(friend,
                                        MainHandler.username, true);
                    }
                });


                Button declineBtn = new Button("Block user");
                declineBtn.setStyle("-fx-font-family: 'FontAwesome'");
                declineBtn
                        .setGraphic(GlyphFontRegistry
                                .font("FontAwesome")
                                .create(FontAwesome.Glyph.TIMES_CIRCLE_ALT));
                declineBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent event) {
                        dbAdaptor
                                .considerRequest(friend,
                                        MainHandler.username, false);
                    }
                });

                currFriend.getChildren().addAll(sender, acceptBtn, declineBtn);
                currFriend.setAlignment(Pos.CENTER);
                currFriend.setSpacing(30);

                listOfPendingReq.getItems().add(currFriend);
            }
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(35);
            columnConstraints.setHalignment(HPos.CENTER);
            friendsPane.getColumnConstraints().add(columnConstraints);
            friendsPane.getColumnConstraints().add(columnConstraints);
            friendsPane.setHgap(160);
            friendsPane.add(pendingReqTitle, 0, 0);
            friendsPane.add(listOfPendingReq, 0, 2);
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
                if (event.getButton() == MouseButton.SECONDARY && !friends.contains(row.getItem().getUsername())) {
                        attachAddFriendPopOver(row);
                }
            });
            return row;
        });

        ArrayList<User> friendsList = new ArrayList<>();

        if (!friends.isEmpty()) {

            for (String friend : friends) {
                friendsList.add(new User(friend,dbAdaptor.getTotalScore(friend)));
            }
        }
        Collections.sort(friendsList, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if (o1.getTotalScore() < o2.getTotalScore()) {
                    return 1;
                } else if (o1.getTotalScore() > o2.getTotalScore()) {
                    return -1;
                } else {
                    return o1.getUsername().compareToIgnoreCase(o2.getUsername());
                }
            }
        });

        friendsTable.getItems().addAll(friendsList);
        new ZoomInRight(friendsTable).play();


    }

    /**
     * Attaches add friend pop over.
     * @param row the row where the pop over is attached
     */
    private void attachAddFriendPopOver(TableRow<User> row) {

        Button addFriendBtn = new Button();
        addFriendBtn.setText("Add friend");
        addFriendBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                String sender = MainHandler.username;
                String recipient = row.getTableView().getItems().get(0).getUsername();
                dbAdaptor.sendFriendReq(sender,recipient);
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
        User searchedUser = dbAdaptor.getUser(username);
        if (username.equals(MainHandler.username)) {
            this.alertBuilder
                    .formEntryWarning("search bar",
                            "You cannot befriend yourself!");
        } else if (!searchedUser.hasUsername()) {
            this.alertBuilder
                    .formEntryWarning("search bar",
                            "There is no user with this username");
        } else {
            friendsTable.getItems().clear();
            friendsTable.getItems()
                    .add(new User(searchedUser.getUsername(),
                            searchedUser.getTotalScore()));
        }
        searchBar.setText("");

    }

    private void reloadPage(ActionEvent ev) {
        new LoginController().redirectToView(ev,"main");
    }
}
