package gui;

import animatefx.animation.ZoomInRight;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
     * Constant for spacing.
     * {@value}
     */
    private static final int SPACING_VAL = 30;
    /**
     * Constant for width.
     * {@value}
     */
    private static final int PERC_WIDTH_VAL = 35;
    /**
     * Constant for hgap.
     * {@value}
     */
    private static final int HGAP_VAL = 160;

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
    private ListView friendsListView;
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

    /**
     * Friend Leaderboard.
     */
    @FXML
    private TableView<User> friendsTable;
    /**
     * For notifications.
     */
    private InformationBuilder informationBuilder = new InformationBuilder();

    /**
     * For reloading the page.
     */
    private MainController mainController;
    /**
     * For generating usefull info.
     */
    private PopOver popOver = new PopOver(null);

    /**
     * Upon initialization, it triggers.
     */
    @FXML
    public void initialize() {
        pendingRequests = (ArrayList<String>) dbAdaptor
                .getRequest(MainHandler.username);
        friends = (ArrayList<String>) dbAdaptor
                .getFriends(MainHandler.username);

        informationBuilder
                .addInformationIconToSearchBox(searchInfoLabel,
                        "Right-click on a user to add them");

        constructTableFriends();
        constructPendingListView();
    }


    /**
     * Construct the leaderboard as table view.
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
                if (event.getButton() == MouseButton.SECONDARY) {
                    if (!friends.contains(row.getItem().getUsername())) {
                        attachAddFriendPopOver(row);
                    } else {
                        attachRemoveFriendPopOver(row);
                    }
                }
            });
            return row;
        });

        ArrayList<User> friendsList = new ArrayList<>();

        if (!friends.isEmpty()) {

            for (String friend : friends) {
                friendsList.add(new User(friend,
                        dbAdaptor.getTotalScore(friend)));
            }
        }
        Collections.sort(friendsList, new Comparator<User>() {
            @Override
            public int compare(final User o1, final User o2) {
                if (o1.getTotalScore() < o2.getTotalScore()) {
                    return 1;
                } else if (o1.getTotalScore() > o2.getTotalScore()) {
                    return -1;
                } else {
                    return o1.getUsername()
                            .compareToIgnoreCase(o2.getUsername());
                }
            }
        });

        friendsTable.getItems().addAll(friendsList);
        new ZoomInRight(friendsTable).play();


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
                        try {
                            mainController.loadFriendsListScene();
                        } catch (IOException err) {
                            err.getMessage();
                        }
                    }
                });

                Button declineBtn = new Button("Block user");
                declineBtn.setStyle("-fx-font-family: 'FontAwesome'");
                declineBtn
                        .setGraphic(GlyphFontRegistry
                                .font("FontAwesome")
                                .create(FontAwesome.Glyph.TIMES_CIRCLE_ALT));

                HBox currFriend = new HBox();
                Label sender = new Label(friend + " sent you a request!");

                declineBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent event) {
                        dbAdaptor
                                .considerRequest(friend,
                                        MainHandler.username, false);
                        try {
                            mainController.loadFriendsListScene();
                        } catch (IOException err) {
                            err.getMessage();
                        }
                    }
                });

                currFriend.getChildren().addAll(sender, acceptBtn, declineBtn);
                currFriend.setAlignment(Pos.CENTER);
                currFriend.setSpacing(SPACING_VAL);

                listOfPendingReq.getItems().add(currFriend);
            }
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(PERC_WIDTH_VAL);
            columnConstraints.setHalignment(HPos.CENTER);
            friendsPane.getColumnConstraints().add(columnConstraints);
            friendsPane.setHgap(HGAP_VAL);
            friendsPane.add(pendingReqTitle, 0, 0);
            friendsPane.add(listOfPendingReq, 0, 2);
        }
    }


    /**\
     * Shows a button for deleting a fiend.
     * @param row the row on which the friend is displayed
     */
    private void attachRemoveFriendPopOver(final TableRow row) {
        Button removeFriendBtn = new Button();
        removeFriendBtn.setText("Remove friend");
        removeFriendBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                String sender = MainHandler.username;
                String recipient =
                        ((User) row.getItem()).getUsername();
                dbAdaptor.considerRequest(sender, recipient, false);
                dbAdaptor.considerRequest(recipient, sender, false);
                reloadPage();
            }
        });
        VBox removeFriendBox = new VBox();
        removeFriendBox.getChildren().add(removeFriendBtn);
        popOver = new PopOver(removeFriendBox);
        popOver.show(row);
    }

    /**
     * Attaches add friend pop over.
     *
     * @param row the row where the pop over is attached.
     */
    private void attachAddFriendPopOver(final TableRow row) {

        Button addFriendBtn = new Button();
        addFriendBtn.setText("Add friend");
        addFriendBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                String sender = MainHandler.username;
                String recipient =
                        ((User) row.getItem()).getUsername();
                dbAdaptor.sendFriendReq(sender, recipient);
                reloadPage();
            }
        });
        VBox addFriendBox = new VBox();
        addFriendBox.getChildren().add(addFriendBtn);
        popOver = new PopOver(addFriendBox);
        popOver.show(row);
    }

    /**
     * Used to reload the page.
     */
    private void reloadPage() {
        try {
            mainController.loadFriendsListScene();
        } catch (IOException ex) {
            alertBuilder.displayException(ex);
        }
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
            //friendsListView.getItems().clear();
            showSearchedFriend(searchedUser);
        }
        searchBar.setText("");

    }

    /**
     * Shows searched friend.
     * @param searchedUser User Object
     */
    private void showSearchedFriend(final User searchedUser) {
        friendsTable.getItems().clear();
        friendsTable.getItems()
                .add(searchedUser);
    }

    /**
     * Sets a new main controller.
     * @param main the controller to be set.
     */
    public void setMainController(final MainController main) {
        this.mainController = main;
    }


}