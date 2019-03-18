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

    /**
     * sets username.
     * @param userName to be set.
     */
    public void setUsername(final String userName) {
        this.username = userName;
    }

    /**
     * sets password.
     * @param passWord to be set.
     */
    public void setPassword(final String passWord) {
        this.password = passWord;
    }

    /**
     * Equals method.
     * @param obj to be checked
     * @return true if equal, false if not
     */
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof LoginCredentials) {
            LoginCredentials that = (LoginCredentials) obj;

            return that.getPassword().equals(this.getPassword())
                    && that.getUsername().equals(this.getUsername());
        }
        return false;
    }

    /**
     * Hashes.
     * @return returns the hash code.;
     */
    @Override
    public int hashCode() {
        return 0;
    }

}
