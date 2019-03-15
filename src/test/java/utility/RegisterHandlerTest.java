package utility;

import exceptions.ServerStatusException;
import gui.AlertBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RegisterHandlerTest {
    private static final String NULL = "";
    private static final String USER = "user";
    private static final String PASS = "pass";
    private static final String ADDITION = "123";
    private static final String PASS_TOMD5 = "1A1DC91C907325C69271DDF0C944BC72";
    private static final String DOMAIN = "http://localhost:8080";

    private static final RegisterHandler testObject = new RegisterHandler(DOMAIN);

    @BeforeEach
    public void initMocks() {
        testObject.httpHandler = Mockito.mock(HttpRequestHandler.class);
        testObject.alert = Mockito.mock(AlertBuilder.class);
    }

    @Test
    public void regUserNull() {
        assertFalse(testObject.registerSubmit(NULL, PASS, PASS),
                "Handler allowed register without username.");
    }
    @Test
    public void regPassNull() {
        assertFalse(testObject.registerSubmit(USER, NULL, PASS),
                "Hanlder allowed register without password.");
    }
    @Test
    public void regConfpassNull() {
        assertFalse(testObject.registerSubmit(USER, PASS, NULL),
                "Handler allowed register without confirming pass");
    }
    @Test
    public void regPassMissmatch() {
        assertFalse(testObject.registerSubmit(USER, PASS,
                PASS + ADDITION), "Handler allowed register"
                + "when passwords didn't match");
    }
    @Test
    public void regFailure() throws Exception{
        when(testObject.httpHandler.reqPost("/register",
                new AccountMessage(USER, PASS_TOMD5))).thenThrow(
                new ServerStatusException(401));
        boolean result = testObject.registerSubmit(USER, PASS, PASS);
        verify(testObject.httpHandler).reqPost("/register",
                new AccountMessage(USER, PASS_TOMD5));
        assertFalse(result);
    }
    @Test
    public void regSucceed() {
        assertTrue(testObject.registerSubmit(USER, PASS, PASS),
                "Valid login credentials were rejected.");
    }
    @Test
    public void simulateEncError() throws Exception {
        given(testObject.httpHandler.reqPost("/register",
                new AccountMessage(USER, PASS_TOMD5))).willAnswer(
                invocation -> { throw new NoSuchAlgorithmException(); });
        boolean result = testObject.registerSubmit(USER, PASS, PASS);
        assertFalse(result);
    }
}
