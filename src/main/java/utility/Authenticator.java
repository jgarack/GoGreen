package utility;

import exceptions.DataConflictException;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that can keep track of registered accounts and can check if the
 * provided
 * account is registered.
 * @author awjvanvugt
 */
public class Authenticator {
    /**
     * List of registered accounts.
     * Authentication happens internal, therefore there is no get method for
     * this
     * Object.
     */
    private final List<AccountMessage> accounts;

    /**
     * Constructs an Authenticator Object with an empty list of accounts.
     */
    public Authenticator() {
        accounts = new ArrayList<AccountMessage>();
    }

    /**
     * Authenticates the input user.
     * @param user account to authenticate.
     * @return whether the username is registered AND the password is correct.
     */
    public boolean authenticate(final AccountMessage user) {
        if (accounts.contains(user)) {
            return true;
        }
        return false;
    }

    /**
     * Registers an account for the input user.
     * @param user account to register.
     * @return true if the account was added correctly.
     * @throws DataConflictException if the username provided is already
     * registered.
     */
    public boolean registerNewUser(final AccountMessage user)
            throws DataConflictException {
        for (AccountMessage account : accounts) {
            if (account.getUsername().equals(user.getUsername())) {
                throw new DataConflictException("Username is already taken.");
            }
        }
        return accounts.add(user);
    }
}
