package utility;

import exceptions.DataConflictException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.util.AssertionErrors.assertTrue;

public class AuthenticatorTest {


    Authenticator authenticator =  new Authenticator();

    AccountMessage listedMock = Mockito.mock(AccountMessage.class);
    AccountMessage mockedArg = Mockito.mock(AccountMessage.class);

    AccountMessage sameAccount1 = new AccountMessage("user", "pass");
    AccountMessage sameAccount2 = new AccountMessage("user", "pass");
    AccountMessage otherPass = new AccountMessage("user", "nopass");
    AccountMessage unknownUser = new AccountMessage("notuser", "pass");

    @BeforeEach
    public void setUpMock(){
        //stubbing
        when(listedMock.getUsername()).thenReturn("user");
        when(mockedArg.getUsername()).thenReturn("user");
    }

    @Test
    public void registerSucceedEmpty() {
        try {
            assertTrue("Authenticator should register user",
                    authenticator.registerNewUser(mockedArg));
        } catch(DataConflictException exception) {
            exception.printStackTrace();
            fail(exception.getMessage());
        }
    }
    @Test
    public void registerSucceed(){
        try {
            when(listedMock.getUsername()).thenReturn("overridden stub");
            assertTrue("Authenticator should register user",
                    authenticator.registerNewUser(mockedArg));
        } catch(DataConflictException exception) {
            exception.printStackTrace();
            fail(exception.getMessage());
        }
    }
    @Test
    public void registerFail() {
        try {
            authenticator.registerNewUser(listedMock);
            assertThrows(DataConflictException.class, () ->
                    authenticator.registerNewUser(mockedArg),
                    "the username user is already taken");
            verify(mockedArg).getUsername();
            verify(listedMock).getUsername();
        } catch(DataConflictException exception) {
            exception.printStackTrace();
            fail(exception.getMessage());
        }
    }
    @Test
    public void authenticateEmptyList() {
        assertFalse(authenticator.authenticate(sameAccount1),
                "No accounts registered but user was authenticated");
    }
    @Test
    public void authenticateSame() {
        try {
            authenticator.registerNewUser(sameAccount1);
            assertTrue("Registered account was not authenticated.",
                    authenticator.authenticate(sameAccount1));
        } catch(DataConflictException exception) {
            exception.printStackTrace();
            fail(exception.getMessage());
        }
    }
    @Test
    public void authenticateTrue() {
        try {
            authenticator.registerNewUser(unknownUser);
            authenticator.registerNewUser(sameAccount1);
            assertTrue("Registered account was not authenticated.",
                    authenticator.authenticate(sameAccount2));
        } catch(DataConflictException exception) {
            exception.printStackTrace();
            fail(exception.getMessage());
        }
    }
    @Test
    public void authenticateWrongPass(){
        try {
            authenticator.registerNewUser(sameAccount1);
            assertFalse(authenticator.authenticate(otherPass),
                    "Wrong password was accepted.");
        } catch(DataConflictException exception) {
            exception.printStackTrace();
            fail(exception.getMessage());
        }
    }
    @Test
    public void authenticateUnknownAccount() {
        try {
            authenticator.registerNewUser(sameAccount1);
            assertFalse(authenticator.authenticate(unknownUser),
                    "Unknown account was authenticated.");
        } catch(DataConflictException exception) {
            exception.printStackTrace();
            fail(exception.getMessage());
        }
    }
}
