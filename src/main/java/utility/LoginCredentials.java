package utility;

import sun.rmi.runtime.Log;

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

    /**
     * sets username.
     * @param username to be set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * sets password.
     * @param password to be set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean equals(Object Obj){
        if(this == Obj) {return true;}

        if(Obj instanceof LoginCredentials){
            LoginCredentials that = (LoginCredentials) Obj;

            return that.getPassword().equals(this.getPassword()) && that.getUsername().equals(this.getUsername());
        }
        return false;
    }

}
