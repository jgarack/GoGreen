package utility;

import Exceptions.DataConflictException;

import java.util.ArrayList;
import java.util.List;

public class Authenticator {
    private List<AccountMessage> accounts;

    public Authenticator() {
        accounts = new ArrayList<AccountMessage>();
    }

    public boolean authenticate(final AccountMessage user) {
        if(accounts.contains(user)) return true;
        return false;
    }

    public boolean registerNewUser(final AccountMessage user)
            throws DataConflictException{
        for(AccountMessage account : accounts){
            if(account.getUsername().equals(user.getUsername()))
                throw new DataConflictException("Username is already taken.");
        }
        return accounts.add(user);
    }
}
