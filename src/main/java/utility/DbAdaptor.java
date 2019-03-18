package utility;

import features.Feature;
import gui.AlertBuilder;

import java.net.URI;
import java.net.URISyntaxException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


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

    /**
     * Initial connection.
     */
    private Connection conn = null;
    /**
     * Initial statement.
     */
    private Statement stmt = null;
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
        System.out.println("connecting to database...");
        try {
            conn = DriverManager.getConnection(jdbUrl, username, password);
            System.out.println("connected to database");
        } catch (SQLException e) {
            System.out.println("connection failure");
            e.printStackTrace();
        }
    }

    /**
     * Disconnect method.
     */
    public void disconnect() {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
            if (rs != null) {
                rs.close();
            }
            System.out.println("Disconnected from Database");

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
        System.out.println("Inserting...");

        try {
            PreparedStatement st = conn
                    .prepareStatement("INSERT INTO users (username,"
                            + "total_score)"
                            + " VALUES(?,?)");
            st.setString(one, user.getUsername());
            st.setInt(two, user.getTotalScore());
            st.executeUpdate();
            st.close();
            System.out.println("Inserted");

        } catch (SQLException e) {
            System.out.println("Not Inserted");
            e.printStackTrace();
        }

    }

    /**
     * Inserts a habit into the DB.
     *
     * @param habitName the habit to be inserted.
     */
    public void insertActivity(final String habitName) {
        try {
            PreparedStatement st = conn
                    .prepareStatement("INSERT INTO activity "
                            + "(name) VALUES (?)");
            st.setString(1, habitName);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Adds activity to a user.
     * @param activity object that holds
     *                 all needed fields for activity relation.
     */
    public void addActivity(final ActivityDb activity) {
        try {
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

            updateTotalScore(activity.getUsernameAct());


        } catch (SQLException e) {
            e.printStackTrace();
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
            System.out.println(st.toString());
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
            System.out.println(st.toString());
            st.executeUpdate();
            st.close();

            disconnect();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * authentication of user who is logging in.
     * @param logCre object with username and password fields.
     * @return boolean true if ok false if not ok.
     */
    public boolean comparecredentials(final LoginCredentials logCre) {


        try {


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
            PreparedStatement st = conn
                    .prepareStatement("INSERT INTO "
                            + "credentials(username, password,"
                            + " question, answer) VALUES (?,?,?,?)");
            st.setString(one, regCre.getUsername());
            st.setString(two, regCre.getPassword());
            st.setString(three, regCre.getQuestion());
            st.setString(four, regCre.getAnswer());
            st.executeUpdate();
            st.close();

            st = conn
                    .prepareStatement("INSERT INTO "
                            + "users(username, total_score) VALUES (?,?)");
            st.setString(one, regCre.getUsername());
            st.setInt(two, 0);
            st.executeUpdate();
            st.close();

            st = conn
                    .prepareStatement("INSERT INTO "
                            + "activities(activity_id, score, player, amount)"
                            + " VALUES (?,?,?,?)");
            st.setInt(one, 1);
            st.setInt(two, 0);
            st.setString(three, regCre.getUsername());
            st.setInt(four, 0);
            st.executeUpdate();
            st.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
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


            PreparedStatement st = conn.prepareStatement(
                    "SELECT * FROM users WHERE username = ?");

            st.setString(1, userName);
            rs = st.executeQuery();

            User tempUser = new User(null, 0);
            while (rs.next()) {
                tempUser.setUsername(rs.getString(one));
                tempUser.setTotalScore(rs.getInt(two));
                break;
            }

            return tempUser;

        } catch (SQLException e) {
            e.printStackTrace();
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
            PreparedStatement st = conn.prepareStatement(new StringBuilder("SELECT amount FROM")
                    .append(" activities WHERE player = ?")
                    .append(" AND activity_id = ?")
                    .toString());
            st.setString(one, name);
            st.setInt(two, activityID);
            System.out.println(name);
            System.out.println(st.toString());
            rs = st.executeQuery();
            System.out.println(rs.next());
            amount += rs.getInt("amount");
            PreparedStatement pst = conn.prepareStatement("UPDATE activities SET amount = ? "
                    + "WHERE player = ? AND activity_id = ?");
            pst.setInt(one, amount);
            pst.setString(two, name);
            pst.setInt(three, activityID);
            pst.executeUpdate();
            pst.close();
            disconnect();
            System.out.println(rs.toString());
            calculateScore(name, activityID, amount);
            updateTotalScore(name);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public void calculateScore(final String username, final int activityID,
                                final int amount) {
        try {
            connect();
            System.out.println("update vegmeal for amount: " + amount);
            PreparedStatement st = conn.prepareStatement(new StringBuilder(
                    "UPDATE activities SET score = ? WHERE player = ")
                    .append("?").append(" AND activity_id = ")
                    .append("?").toString());
            int score = new Feature("1").vegmeal_calcScore(amount);
            System.out.println("score is now " + score);
            st.setInt(one, score);
            st.setString(two, username);
            st.setInt(three, activityID);
            st.executeUpdate();
            st.close();
            disconnect();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * returns activity amount.
     *
     * @param name   username whose activity amount should be returned.
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
            System.out.println(st.toString());
            rs = st.executeQuery();
            System.out.println(rs.next());
            int ret = rs.getInt(one);
            disconnect();
            return ret;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * returns total_score.
     *
     * @param name which total_score should be returned.
     * @return total_score
     */
    public int getTotalScore(final String name) {
        try {
            System.out.println(name);
            connect();
            String query = "SELECT total_score FROM users WHERE username = ?";
            PreparedStatement st = conn.prepareStatement(query.toString());
            st.setString(one, name);
            System.out.println(st.toString());
            rs = st.executeQuery();
            System.out.println(rs.next());
            int ret = rs.getInt("total_score");
            disconnect();
            return ret;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }


}
