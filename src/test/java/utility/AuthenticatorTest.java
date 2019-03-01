package utility;

import exceptions.DataConflictException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.testng.annotations.BeforeMethod;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

public class AuthenticatorTest {
    Authenticator authenticator =  new Authenticator();

    AccountMessage listedMock1 = Mockito.mock(AccountMessage.class);
    AccountMessage listedMock2 = Mockito.mock(AccountMessage.class);
    AccountMessage listedMock3 = Mockito.mock(AccountMessage.class);
    AccountMessage mockedArg = Mockito.mock(AccountMessage.class);
    AccountMessage unknownMock = Mockito.mock(AccountMessage.class);

    @BeforeMethod
    public void setUpMock(){
        //check if mocking was done correctly
        when(mockedArg.equals(listedMock1)).thenThrow(new IllegalAccessException("wrong order call detected"));

        //stubbing
        //mockedArg == listedMock3
        when(listedMock1.equals(mockedArg)).thenReturn(false);
        when(listedMock2.equals(mockedArg)).thenReturn(false);
        when(listedMock3.equals(mockedArg)).thenReturn(true);
        //unknownMock is not a registered account
        when(listedMock1.equals(unknownMock)).thenReturn(false);
        when(listedMock2.equals(unknownMock)).thenReturn(false);
        when(listedMock3.equals(unknownMock)).thenReturn(false);
        //define usernames where necessary
        when(listedMock1.getUsername()).thenReturn("user1");
        when(listedMock2.getUsername()).thenReturn("user2");
        when(listedMock3.getUsername()).thenReturn("user3");
        when(mockedArg.getUsername()).thenReturn("user3");
    }

    @Test
    public void registerSucceed() {
        try {
            assertTrue("Authenticator should register user3", authenticator.registerNewUser(mockedArg));
        } catch(DataConflictException exception) {
            fail(exception.getMessage());
        }
    }
    @Test
    public void registerFail() {
        try{
            authenticator.registerNewUser(listedMock3);
            System.out.println(mockedArg.getUsername());
            assertThrows(DataConflictException.class, () -> authenticator.registerNewUser(mockedArg), "the username user3 is already taken");
        } catch(DataConflictException exception) {
            fail(exception.getMessage());
        }
    }
}
