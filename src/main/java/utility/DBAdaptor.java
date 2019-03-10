package utility;

import java.net.URI;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 * Adapter class for the database.
 */
public class DBAdaptor {

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
    public DBAdaptor() {
        try {
            URI dbUri = new URI("postgres://ruyhsamtzksdvj:"
                    + "6361f4ca393416fc4786d99876594abeec8f"
                    + "66e6b86eb2502c7420a588729f65@ec2-54-217-207-242"
                    + ".eu-west-1.compute.amazonaws.com:5432/d360178ojdevbl");

            this.username = dbUri.getUserInfo().split(":")[0];
            this.password = dbUri.getUserInfo().split(":")[1];
            this.jdbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':'
                    + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";
        } catch (Exception e) {
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
     * @param userName userName of the user.
     * @param encPassword encrypted pass of the user.
     * @param dateOfBirth birth date of the user.
     * @param gender gender of the user.
     * @param questionId id of secret question of the user.
     * @param answer answer to the secret question
     * @param totalScore total score of the user.
     */
    public void insertUser(final String userName, final String encPassword,
                           final String dateOfBirth, final String gender,
                           final int questionId, final String answer,
                           final int totalScore) {
        System.out.println("Inserting...");

        try {
            PreparedStatement st = conn
                    .prepareStatement("INSERT INTO users (username,"
                            + " enc_password, date_of_birth,"
                            + " gender, question_id,"
                            + " answer, total_score) VALUES(?,?,?,?,?,?,?)");
            st.setString(one, userName);
            st.setString(two, encPassword);
            st.setString(three, dateOfBirth);
            st.setString(four, gender);
            st.setInt(five, questionId);
            st.setString(six, answer);
            st.setInt(seven, totalScore);
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
                            + "(activity) VALUES (?)");
            st.setString(1, habitName);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Inserts Question into the DB.
     * @param question the question to be inserted.
     */
    public void insertQuestion(final String question) {
        try {
            PreparedStatement st = conn
                    .prepareStatement("INSERT INTO question"
                            + "(question) VALUES (?)");
            st.setString(1, question);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds friends to users friend list.
     * @param friend1 Friend to be added.
     * @param friend2 Friend to be added.
     */
    public void makeFriends(final int friend1, final int friend2) {
        try {
            PreparedStatement st = conn
                    .prepareStatement("INSERT INTO "
                            + "friends(friend_1, friend_2) VALUES (?,?)");
            st.setInt(1, friend1);
            st.setInt(2, friend2);
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
     * Gets user from the DB.
     * @param userName username upon which a user is searched.
     */
    public void getUser(final String userName) {
        try {
            PreparedStatement st = conn
                    .prepareStatement("SELECT * "
                            + "FROM users WHERE username = ?");
                 st.setString(1, userName);
                 rs = st.executeQuery();

                 while (rs.next()) {
                    User tempUser = new User(-1, null,
                            null, null, -1,
                            null, -1, null);
                    tempUser.setUserId(rs.getInt(1));
                    tempUser.setUsername(rs.getString(2));
                    tempUser.setEncPassword(rs.getString(three));
                    tempUser.setGender(rs.getString(four));
                    tempUser.setQuestionId(rs.getInt(five));
                    tempUser.setAnswer(rs.getString(six));
                    tempUser.setTotalScore(rs.getInt(seven));
                    tempUser.setDateOfBirth(rs.getString(eight));

                    System.out.println(tempUser.toString());


                }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
