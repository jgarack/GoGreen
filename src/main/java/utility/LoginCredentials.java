package utility;

/**
 * Class for the login credentials.
 */
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
     * @param userName the username.
     * @param pass the password.
     */
    public LoginCredentials(final String userName, final String pass) {
        this.username = userName;
        this.password = pass;
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
