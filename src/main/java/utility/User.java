package utility;

import java.util.List;

/**
 * Class for User.
 */
public class User {

    /**
     * Username of the user.
     */
    private String username;
    /**
     * The gender of the user.
     */
    private String gender;
    /**
     * Total score of the user.
     */
    private int totalScore;
    /**
     * Date of birth of the user.
     */
    private String dateOfBirth;
    /**
     * Friend list of the user.
     */
    private List<String> friends;

    /**
     * Default constructor of the User class.
     * @param userName username of the user.
     * @param sex Gender of the user.
     * @param userTotalScore Total score of the user.
     * @param birthDate Date of birth of the user.
     */
    public User(final String userName, final String sex,
                final int userTotalScore, final String birthDate) {
        this.username = userName;
        this.gender = sex;
        this.totalScore = userTotalScore;
        this.dateOfBirth = birthDate;


        //TODO implement friends querry;
    }


    /**
     * Retrieves username of the user.
     * @return the username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets a new username to the user.
     * @param userName the username to be set.
     */
    public void setUsername(final String userName) {
        this.username = userName;
    }

    /**
     * Retrieves gender of the user.
     * @return the gender of the user.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets a new gender to the user.
     * @param genderUser the gender to be set.
     */
    public void setGender(final String genderUser) {
        this.gender = genderUser;
    }

    /**
     * Retrieves total score of the user.
     * @return the total score of the user.
     */
    public int getTotalScore() {
        return totalScore;
    }

    /**
     * Sets a new total score to the user.
     * @param userTotalScore the total score to be set.
     */
    public void setTotalScore(final int userTotalScore) {
        this.totalScore = userTotalScore;
    }

    /**
     * Retrieves date of birth of the user.
     * @return the date of birth of the user.
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets a new date of birth to the user.
     * @param birthDate the date of birth to be set.
     */
    public void setDateOfBirth(final String birthDate) {
        this.dateOfBirth = birthDate;
    }

    /**
     * returns list of friends.
     * @return list of friends: String.
     */
    public List<String> getFriends() {
        return this.friends;
    }

    /**
     * Adds a username to the list.
     * @param friendUsername username of the friend to add.
     */
    public void addFriend(final String friendUsername) {
        this.friends.add(friendUsername);
    }

    /**
     * Generates a string representation of the user.
     * @return A human-friendly string.
     */
    @Override
    public String toString() {
        return "User{"
                + ", username='" + username + '\''
                + ", gender='" + gender + '\''
                + ", totalScore=" + totalScore
                + ", dateOfBirth='" + dateOfBirth.toString() + '\'' + '}';
    }
}
