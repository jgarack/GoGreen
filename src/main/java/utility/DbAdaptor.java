package utility;

import gui.AlertBuilder;

import java.net.URI;
import java.net.URISyntaxException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;



/**
 * Adapter class for the database.
 */
public class DbAdaptor {

    /**
     * Magic number 1.
     */
    private int one = 1;
    /**
     * Magic number 2.
     */
    private int two = 2;
    /**
     * Magic number 3.
     */
    private final int three = 3;
    /**
     * Magic number 4.
     */
    private final int four = 4;
    /**
     * Magic number 5.
     */
    private final int five = 5;
    /**
     * Magic number 6.
     */
    private final int six = 6;
    /**
     * Magic number 7.
     */
    private final int seven = 7;
    /**
     * Magic number 8.
     */
    private final int eight = 8;


    private enum FRIEND_STATUS {
        PENDING,
        ACCEPTED,
        DECLINED}


    /**
     * Url of the database.
     */
    private String jdbUrl;
    /**
     * Username for the DB.
     */
    private String username;
    /**
     * Password for the DB.
     */
    private String password;

    private AlertBuilder alertBuilder = new AlertBuilder();

    /**
     * Initial connection.
     */
    private Connection conn = null;

    /**
     * Initial set of results from DB.
     */
    private ResultSet rs = null;

    /**
     * Constructor assigns values to username, password, and connection string.
     */
    public DbAdaptor() {

        try {
            URI dbUri = new URI("postgres://ruyhsamtzksdvj:"
                    + "6361f4ca393416fc4786d99876594abeec8f"
                    + "66e6b86eb2502c7420a588729f65@ec2-54-217-207-242"
                    + ".eu-west-1.compute.amazonaws.com:5432/d360178ojdevbl");

            this.username = dbUri.getUserInfo().split(":")[0];
            this.password = dbUri.getUserInfo().split(":")[1];
            this.jdbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':'
                    + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


    }

    /**
     * Connect method.
     */
    public void connect() {
        try {
            conn = DriverManager.getConnection(jdbUrl, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Disconnect method.
     */
    public void disconnect() {
        try {
            if (conn != null) {
                conn.close();
            }
            if (rs != null) {
                rs.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserts user into the DB.
     *
     * @param user object of the user.
     */
    public void insertUser(final User user) {
        connect();
        try {
            PreparedStatement st = conn
                    .prepareStatement("INSERT INTO users (username,"
                            + "total_score,image)"
                            + " VALUES(?,?,?)");
            st.setString(one, user.getUsername());
            st.setInt(two, user.getTotalScore());
            st.setString(three,user.getAvatarUrl());
            st.executeUpdate();
            st.close();


        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            disconnect();
        }

    }

    /**
     * Adds activity to a user.
     * @param activity object that holds
     *                 all needed fields for activity relation.
     */
    public void addActivity(final ActivityDb activity) {
        try {
            connect();
            PreparedStatement st = conn
                    .prepareStatement("INSERT INTO "
                            + "Activities(player, activity_id,"
                            + " amount, score) VALUES (?,?,?,?)");
            st.setString(one, activity.getUsernameAct());
            st.setInt(two, activity.getActivityId());
            st.setInt(three, activity.getAmount());
            st.setInt(four, activity.getScore());
            st.executeUpdate();
            st.close();



        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    /**
     * updates total_score when inserting to activities table.
     *
     * @param name says which user should have the total_score updated.
     */
    public void updateTotalScore(final String name) {

        try {
            connect();

            PreparedStatement st = conn
                    .prepareStatement("SELECT sum(score) "
                            + "FROM activities "
                            + "WHERE player = ?");
            st.setString(one, name);
            rs = st.executeQuery();
            System.out.println(rs.next());
            int score = Integer.parseInt(rs.getString(one));
            st.close();

            st = conn
                    .prepareStatement("UPDATE users"
                            + " SET total_score = ? "
                            + "WHERE username = ?");
            st.setInt(one, score);
            st.setString(two, name);
            st.executeUpdate();
            st.close();



        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public void updateAvatarUrl(final String name, final String avatarUrl){
        try {
            connect();
            PreparedStatement  st = conn
                    .prepareStatement("UPDATE users"
                            + " SET image = ? "
                            + "WHERE username = ?");
            st.setString(one, avatarUrl);
            st.setString(two, name);
            st.executeUpdate();
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    /**
     * authentication of user who is logging in.
     * @param logCre object with username and password fields.
     * @return boolean true if ok false if not ok.
     */
    public boolean comparecredentials(final LoginCredentials logCre) {


        try {

            connect();
            PreparedStatement st = conn.prepareStatement(
                    "SELECT username,"
                            + " password FROM credentials WHERE username = ?");


            st.setString(1, logCre.getUsername());
            rs = st.executeQuery();

            LoginCredentials tempLC = new LoginCredentials(null, null);


            while (rs.next()) {
                tempLC.setUsername(rs.getString(one));
                tempLC.setPassword(rs.getString(two));
            }

            if (logCre.equals(tempLC)) {
                return true;
            }

            return false;


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }


        return false;
    }

    /**
     * adds newly registered user to the database.
     *
     * @param regCre object that holds all data for registering.
     * @return returns true iff the adding has been successful.
     */
    public boolean addNewUser(final RegisterCredentials regCre) {

        try {
            connect();
            PreparedStatement st = conn
                    .prepareStatement("INSERT INTO "
                            + "users(username, total_score, image) VALUES (?,?,?)");
            st.setString(one, regCre.getUsername());
            st.setInt(two, 0);
            st.setString(three,"/icons/avatar1.png");
            st.executeUpdate();
            st.close();


            st = conn.prepareStatement("INSERT INTO "
                            + "credentials(username, password,"
                            + " question, answer) VALUES (?,?,?,?)");
            st.setString(one, regCre.getUsername());
            st.setString(two, regCre.getPassword());
            st.setString(three, regCre.getQuestion());
            st.setString(four, regCre.getAnswer());
            st.executeUpdate();
            st.close();

            for (int i = 1; i < 7;i++) {
                addActivity(new ActivityDb(i,0, 0, regCre.getUsername()));
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

        return false;
    }

    /**
     * Gets user from the DB.
     *
     * @param userName username upon which a user is searched.\
     * @return User info.
     */
    public User getUser(final String userName) {
        try {

            connect();
            PreparedStatement st = conn.prepareStatement(
                    "SELECT username, total_score, image FROM users WHERE username = ?");

            st.setString(1, userName);
            rs = st.executeQuery();

            User tempUser = new User(null, 0);

            while (rs.next()) {
                tempUser.setUsername(rs.getString(one));
                tempUser.setTotalScore(rs.getInt(two));
                tempUser.setAvatarUrl(rs.getString(three));
                break;
            }

            return tempUser;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

        return null;
    }

    /**
     * updates activity.
     *
     * @param name   whose activity should be updated
     * @param activityID which activity should be updated
     * @param amount     by how many should it be updated
     * @return true if worked false if exceptions

    */
    public boolean updateActivity(final String name,
                                  final int activityID,
                                  int amount) {
        try {
            connect();

            PreparedStatement st = conn
                    .prepareStatement(new StringBuilder("SELECT amount FROM")
                    .append(" activities WHERE player = ?")
                    .append(" AND activity_id = ?")
                    .toString());
            st.setString(one, name);

            st.setInt(two, activityID);
            rs = st.executeQuery();
            System.out.println(rs.next());
            amount += rs.getInt("amount");
            PreparedStatement pst = conn
                    .prepareStatement("UPDATE activities SET amount = ? "
                    + "WHERE player = ? AND activity_id = ?");
            pst.setInt(one, amount);
            pst.setString(two, name);
            pst.setInt(three, activityID);
            pst.executeUpdate();
            pst.close();
            calculateScore(name, activityID, amount);
            updateTotalScore(name);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            disconnect();
        }
    }

    /**
     * Calculates score of a given user.
     * @param username the username of the user.
     * @param activityID the activity of the user.
     * @param amount the count of activities.
     */
    public void calculateScore(final String username, final int activityID,
                                final int amount) {
        try {
            connect();
            PreparedStatement st = conn.prepareStatement(new StringBuilder(
                    "UPDATE activities SET score = ? WHERE player = ")
                    .append("?").append(" AND activity_id = ")
                    .append("?").toString());
            st.setInt(one, amount);
            st.setString(two, username);
            st.setInt(three, activityID);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    /**
     * returns activity amount.
     *
     * @param name username whose activity amount should be returned.
     * @param activityID id of the activity
     * @return amount of given activity of given user
     */
    public int getActivityAmount(final String name, final int activityID) {
        try {
            connect();
            StringBuilder query = new StringBuilder(
                    "SELECT score FROM activities WHERE player = ")
                    .append("?").append(" AND activity_id = ")
                    .append("?");
            PreparedStatement st = conn.prepareStatement(query.toString());
            st.setString(one, name);
            st.setInt(two, activityID);
            rs = st.executeQuery();
            System.out.println(rs.next());
            int ret = rs.getInt(one);
            return ret;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return -1;
        } finally {
            disconnect();
        }
    }

    /**
     * returns total_score.
     *
     * @param name which total_score should be returned.
     * @return total_score
     */
    public int getTotalScore(final String name) {
        connect();

        try {
            String query = "SELECT total_score FROM users WHERE username = ?";
            PreparedStatement st = conn.prepareStatement(query.toString());
            st.setString(one, name);
            rs = st.executeQuery();
            System.out.println(rs.next());
            int ret = rs.getInt("total_score");
            return ret;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return -1;
        } finally {
            disconnect();
        }
    }

    /**
     * Deletes by username from the DB.
     * @param username the User that should be deleted.
     */
    public void deleteByUsername(final String username) {
        connect();
        try {

            PreparedStatement st = conn.prepareStatement(
                    "DELETE FROM users WHERE username = ?");

            st.setString(1, username);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    /**
     * send request to be friends method.
     * @param fromUser from which the invitation is send
     * @param toUser user to whom you want to send the invitation
     */
    public void sendFriendReq(final String fromUser, final String toUser) {
        if(checkIfInDb(fromUser,toUser)){
            connect();
            try {
                PreparedStatement st = conn.prepareStatement(
                        "INSERT INTO friend_request(from_user, "
                             + "to_user,friend_status) VALUES (?,?, ?::friend_status)");
                st.setString(1,fromUser);
                st.setString(2,toUser);
                st.setString(3,FRIEND_STATUS.PENDING.name());
                st.executeUpdate();
                //alertBuilder.showInformationNotification("Friend request sent!");

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                disconnect();
            }
        }

    }

    /**
     * Checks if tuple is in the db
     * @param from_us sender
     * @param to_us recipient
     * @return true iff the tuple is in the table.
     */
    public boolean checkIfInDb(final String from_us, final String to_us) {
        boolean check = false;
        connect();
        try {

            PreparedStatement st = conn.prepareStatement("SELECT * FROM friend_request WHERE from_user = ? AND to_user = ?");
            st.setString(2, from_us);
            st.setString(1, to_us);
            rs = st.executeQuery();
            if (rs.next() == false) {
                check = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return check;
    }

    /**
     * method to say if you rejected or accepted the invitation.
     * @param fromUser user who send the inv
     * @param toUser user who got the inv
     * @param accepted rejected - false, accepted - true
     */
    public void considerRequest(final String fromUser,
                                final String toUser, final boolean accepted) {
        connect();
        try {

            PreparedStatement st = conn.prepareStatement("UPDATE friend_request "
                    + "SET friend_status = ?::\"friend_status\" WHERE from_user = ? AND to_user = ?");
            if (accepted) {
                st.setString(1,FRIEND_STATUS.ACCEPTED.name()) ;
            } else st.setString(1,FRIEND_STATUS.DECLINED.name());
            st.setString(2, fromUser);
            st.setString(3, toUser);
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }


    }

    /**
     * returns all pending requests.
     * @param username username who wants the pending requests
     * @return pending requests
     */
    public List<String> getRequest(String username) {
        connect();

        try {
            List<String> listOfPending = new ArrayList<>();
            PreparedStatement st = conn.prepareStatement("SELECT from_user FROM friend_request"
                    + " WHERE to_user = ? and friend_status = ?::\"friend_status\"");
            st.setString(1, username);
            st.setString(2,FRIEND_STATUS.PENDING.name());
            rs = st.executeQuery();
            while (rs.next()) {
                listOfPending.add(rs.getString(1));
            }
            return listOfPending;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

        return null;
    }

    /**
     * returns friends of given user.
     * @param username user who want the friends
     * @return friends of given user
     */
    public List<String> getFriends(String username) {
        connect();
        try {
            List<String> listOfPending = new ArrayList<>();
            PreparedStatement st = conn.prepareStatement("SELECT to_user FROM "
                    + "friend_request WHERE from_user = ? AND friend_status = ?::\"friend_status\"");
            st.setString(1, username);
            st.setString(2,FRIEND_STATUS.ACCEPTED.name());
            rs = st.executeQuery();
            while (rs.next()) {
                listOfPending.add(rs.getString(1));
            }
            st = conn.prepareStatement("SELECT from_user FROM friend_request "
                    + "WHERE to_user = ? AND friend_status = ?::\"friend_status\"");
            st.setString(1, username);
            st.setString(2,FRIEND_STATUS.ACCEPTED.name());
            rs = st.executeQuery();
            while (rs.next()) {
                listOfPending.add(rs.getString(1));
            }
            return listOfPending;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

        return null;
    }

    public void updateDate(String username, Date date){
        connect();
        try {
            PreparedStatement st = conn.prepareStatement("UPDATE users SET date_last_active = ? WHERE username = ?");
            st.setDate(1, date);
            st.setString(2, username);
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

    }

    public Date getDate(String username) {
        connect();
        try {
            PreparedStatement st = conn.prepareStatement("SELECT date_last_active FROM users WHERE username = ?");
            st.setString(1, username);
            rs = st.executeQuery();
            if(rs.next())
                return rs.getDate(1);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            disconnect();
        }
        return null;
    }


    //Achievements

    public void addAchievement (int id, String username){
        connect();
        try {
            PreparedStatement st = conn.prepareStatement("INSERT INTO achieved(username, achievements) VALUES (?,?)");
            st.setString(1, username);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public List<Achievement> getAchievements(String username) {
        connect();
        List<Achievement> temp = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement("SELECT achievements.name FROM achieved, achievements WHERE achieved.achievements = achievements.id AND achieved.username = ?");
            st.setString(1, username);
            rs = st.executeQuery();

            Achievement ach;
            while (rs.next()) {
                ach = new Achievement(rs.getString(1).replace("_", "  "), true);
                temp.add(ach);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return temp;
    }

}
