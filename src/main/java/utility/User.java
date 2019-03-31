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
     * Total score of the user.
     */
    private int totalScore;
    /**
     * Avatar of the user.
     */
    private String avatarUrl;
    /**
     * Friend list of the user.
     */
    private List<String> friends;

    /**
     * Default constructor of the User class.
     * @param userName username of the user.
     * @param userTotalScore Total score of the user.
     */
    public User(final String userName, final int userTotalScore) {
        this.username = userName;
        this.totalScore = userTotalScore;
        this.avatarUrl = "/icons/avatar2.png";

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
     * Checks if the username has been instantiated.
     * @return true iff the username is not null;
     */
    public boolean hasUsername() {
        return this.username != null;
    }


    /**
     * Generates a string representation of the user.
     * @return A human-friendly string.
     */
    @Override
    public String toString() {
        return "User{"
                + ", username='" + username + '\''
                + ", totalScore=" + totalScore + '\'' + '}';
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatar) {
        this.avatarUrl = avatar;
    }
}
