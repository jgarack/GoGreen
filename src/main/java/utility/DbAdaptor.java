package utility;

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
     * @param user object of the user.
     */
    public void insertUser(final User user) {
        System.out.println("Inserting...");

        try {
            PreparedStatement st = conn
                    .prepareStatement("INSERT INTO users (username,"
                                        + " gender,total_score,"
                                        + " date_of_birth)"
                                        + " VALUES(?,?,?,?)");
            st.setString(one, user.getUsername());
            st.setString(two, user.getGender());
            st.setInt(three, user.getTotalScore());
            st.setString(four, user.getDateOfBirth());
            System.out.println(st.toString());
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
     * @param playerId the user to which activity is added.
     * @param activityId the activity to be added.
     * @param dateOfActivity date of the activity.
     * @param score score of the activity.
     */
    public void addActivity(final int playerId, final int activityId,
                            final String dateOfActivity, final int score) {
        try {
            PreparedStatement st = conn
                    .prepareStatement("INSERT INTO "
                            + "Activities(pleye_id, activity_id,"
                            + " date_of_activity, score) VALUES (?,?,?,?)");
            st.setInt(one, playerId);
            st.setInt(two, activityId);
            st.setString(three, dateOfActivity);
            st.setInt(four, score);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * authentication of user who is logging in.
     * @param logCre object with username and password fields.
     * @return boolean true if ok false if not ok.
     */
    public boolean comparecredentials(LoginCredentials logCre) {


        try {


            PreparedStatement st = conn.prepareStatement(
                    "SELECT username, password FROM credentials WHERE username = ?");


            st.setString(1, logCre.getUsername());
            rs = st.executeQuery();

            LoginCredentials tempLC = new LoginCredentials( null, null);


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
     * @param regCre object that holds all data for registering.
     * @return
     */
    public boolean addNewUser(RegisterCredentials regCre) {

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
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Gets user from the DB.
     * @param userName username upon which a user is searched.\
     * @return User info.
     */
    public User getUser(final String userName) {
        try {


            PreparedStatement st = conn.prepareStatement(
                    "SELECT * FROM users WHERE username = ?");

            st.setString(1, userName);
            rs = st.executeQuery();

            User tempUser = new User(null,
                        null, 0, null);

            tempUser.setUsername(rs.getString(one));
            tempUser.setGender(rs.getString(two));
            tempUser.setTotalScore(rs.getInt(three));
            tempUser.setDateOfBirth(rs.getString(four));


            return tempUser;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }



}
