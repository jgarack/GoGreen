package utility;

import java.net.URI;
import java.sql.*;

public class DBAdaptor {

    /**
     * DataBase Variables
     */
    String jdbUrl ;
    String username ;
    String password;

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    /**
     * Constructor assigns values to username, password, and connection string
     */
    public DBAdaptor(){
        try {
            URI dbUri = new URI("postgres://ruyhsamtzksdvj:6361f4ca393416fc4786d99876594abeec8f66e6b86eb2502c7420a588729f65@ec2-54-217-207-242.eu-west-1.compute.amazonaws.com:5432/d360178ojdevbl");

            this.username = dbUri.getUserInfo().split(":")[0];
            this.password = dbUri.getUserInfo().split(":")[1];
            this.jdbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";
        }
        catch(Exception e){
            e.printStackTrace();
        }


    }

    public void connect(){
        System.out.println("connecting to database...");
        try{
            conn = DriverManager.getConnection(jdbUrl, username, password);
            System.out.println("connected to database");
        }
        catch(SQLException e){
            System.out.println("connection failure");
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try{
            if(stmt!=null){
                stmt.close();
            }
            if(conn!=null){
                conn.close();
            }
            if(rs!=null){
                rs.close();
            }
            System.out.println("Disconnected from Database");

        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void insertUser(String username, String enc_password, String date_of_birth,String gender, int question_id,String answer,int total_score){
        System.out.println("Inserting...");

        try {
            PreparedStatement st = conn.prepareStatement("INSERT INTO users (username, enc_password, date_of_birth, gender, question_id, answer, total_score) VALUES(?,?,?,?,?,?,?)");
            st.setString(1,username);
            st.setString(2,enc_password);
            st.setString(3,date_of_birth);
            st.setString(4,gender);
            st.setInt(5,question_id);
            st.setString(6,answer);
            st.setInt(7,total_score);
            st.executeUpdate();
            st.close();
            System.out.println("Inserted");

        } catch (SQLException e) {
            System.out.println("Not Inserted");
            e.printStackTrace();
        }

    }

    public void insertActivity(String habitName){
        try {
            PreparedStatement st = conn.prepareStatement("INSERT INTO activity (activity) VALUES (?)");
            st.setString(1, habitName);
            st.executeUpdate();
            st.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }

    }

    public void insertQuestion(String question){
        try{
            PreparedStatement st = conn.prepareStatement("INSERT INTO question(question) VALUES (?)");
            st.setString(1, question);
            st.executeUpdate();
            st.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void makeFriends(int Friend1, int Friend2){
        try{
            PreparedStatement st = conn.prepareStatement("INSERT INTO friends(friend_1, friend_2) VALUES (?,?)");
            st.setInt(1, Friend1);
            st.setInt(2, Friend2);
            st.executeUpdate();
            st.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addActivity(int playerId, int activityId, String dateOfActivity, int score){
        try{
            PreparedStatement st = conn.prepareStatement("INSERT INTO Activities(pleye_id, activity_id, date_of_activity, score) VALUES (?,?,?,?)");
            st.setInt(1, playerId);
            st.setInt(2, activityId);
            st.setString(3, dateOfActivity);
            st.setInt(4, score);
            st.executeUpdate();
            st.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void getUser (String userName){
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
                 st.setString(1,userName);
                 rs = st.executeQuery();




                while(rs.next()){
                    User tempUser = new User(-1,null,null,null,-1,null,-1,null);
                    tempUser.setUser_id(rs.getInt(1));
                    tempUser.setUsername(rs.getString(2));
                    tempUser.setEncPassword(rs.getString(3));
                    tempUser.setGender(rs.getString(4));
                    tempUser.setQuestion_id(rs.getInt(5));
                    tempUser.setAnswer(rs.getString(6));
                    tempUser.setTotalScore(rs.getInt(7));
                    tempUser.setDateOfBirth(rs.getString(8));

                    System.out.println(tempUser.toString());


                }


        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }



}
