package utility;

public class LoginCredentials {

    /**
     * variable to store username.
     */
    private String username;

    /**
     * variable to store username.
     */
    private String password;

    /**
     * Constructor of the object.
     * @param username the username.
     * @param password the password.
     */
    public LoginCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * returns the username.
     * @return the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * returns the password.
     * @return the password.
     */
    public String getPassword() {
        return password;
    }
}
