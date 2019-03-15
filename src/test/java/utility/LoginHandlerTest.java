package utility;

import exceptions.ServerStatusException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import gui.AlertBuilder;

import java.io.BufferedReader;
import java.rmi.ServerException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class LoginHandlerTest {
    private static final String NULL = "";
    private static final String USER = "user";
    private static final String PASS = "pass";
    private static final String ADDITION = "123";
    private static final String PASS_TOMD5 = "1A1DC91C907325C69271DDF0C944BC72";
    private static final String DOMAIN = "http://localhost:8080";

    private static final LoginHandler testObject = new LoginHandler(DOMAIN);

    @BeforeEach
    public void initMocks() {
        testObject.httpHandler = Mockito.mock(HttpRequestHandler.class);
        testObject.alert = Mockito.mock(AlertBuilder.class);
    }

    @Test
    public void loginUserNull() {
        assertFalse(testObject.loginSubmit(NULL, PASS),
                "Handler allowed login without username.");
    }
    @Test
    public void loginPassNull() {
        assertFalse(testObject.loginSubmit(USER, NULL),
                "Hanlder allowed login without password.");
    }
    @Test
    public void loginFailure() throws Exception{
        try {
            when(testObject.httpHandler.reqPost("/login",
                    new AccountMessage(USER, PASS_TOMD5))).thenThrow(
                            new ServerStatusException(401));
        } catch(Exception e) {
            //Never occurs
        }
        /*assertThrows(ServerStatusException.class, () -> testObject.httpHandler.reqPost("/login",
                new AccountMessage(USER, PASS_TOMD5)));*/
        boolean result = testObject.loginSubmit(USER, PASS);
        verify(testObject.httpHandler).reqPost("/login", new AccountMessage(USER, PASS_TOMD5));
        assertThrows(ServerStatusException.class, () -> testObject.httpHandler.reqPost("/login", new AccountMessage(USER, PASS_TOMD5)));
        assertFalse(result);
    }
    @Test
    public void loginSucceed() {
        assertTrue(testObject.loginSubmit(USER, PASS),
                "Valid login credentials were rejected.");
    }
}