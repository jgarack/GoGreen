package utility;

/** Represents an account to send to the server.
 * @author Jonathan
 */
public class AccountMessage {
    /** The username for this account.**/
    private String fusername;
    /** The password for this account.**/
    private String fpassword;

    /** Constructs an AccountMessage Object.
     * @author Jonathan
     * @param username the username to use for this account
     * @param password the password to use for this account
     */
    public AccountMessage(final String username, final String password) {
        this.fpassword = password;
        this.fusername = username;
    }

    /** Retrieves the username.
     * @return username of this account.
     **/
    public String getUsername() {
        return this.fusername;
    }

    /** Retrieves the password.
     * @return the password of this account.
     **/
    public String getPassword() {
        return this.fpassword;
    }

    /** Sets the password.
     * @param password The new password, must be md5 hashed.
     **/
    public void setPassword(final String password) {
        this.fpassword = password;
    }

    /** Sets the username.
     * @param username The new username.
     **/
    public void setUsername(final String username) {
        this.fusername = username;
    }

    /** Equals method.
     * @author Jonathan
     * @param obj the object to compare
     * @return true iff obj is an instance of AccountMessage and equal to this
     * object
     */
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AccountMessage) {
            AccountMessage that = (AccountMessage) obj;
            return that.getPassword().equals(this.fpassword)
                    && that.getUsername().equals(this.fusername);
        }
        return false;
    }

    /**
     * Produces a hash code for this object.
     * @return the hash code.
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
