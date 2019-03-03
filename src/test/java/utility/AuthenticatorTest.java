package utility;

import exceptions.DataConflictException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertTrue;

public class AuthenticatorTest {
    Authenticator authenticator =  new Authenticator();

    AccountMessage listedMock1 = Mockito.mock(AccountMessage.class);
    AccountMessage listedMock2 = Mockito.mock(AccountMessage.class);
    AccountMessage listedMock3 = Mockito.mock(AccountMessage.class);
    AccountMessage mockedArg = Mockito.mock(AccountMessage.class);
    AccountMessage unknownMock = Mockito.mock(AccountMessage.class);

    @BeforeAll
    public void setUpMock(){
        //stubbing
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
