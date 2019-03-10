package utility;

import exceptions.ServerStatusException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServerStatusExceptionTest {
    private static final int STATUS = 404;
    private static final String MSG = "hello";
    private static final Throwable CAUSE = new Exception();

    @Test
    public void defaultCon() {
        assertThrows(UnsupportedOperationException.class,
                () -> new ServerStatusException(),
                "Constructor without status should throw exception.");
    }
    @Test
    public void msgNoStatus() {
        assertThrows(UnsupportedOperationException.class,
                () -> new ServerStatusException(MSG),
                "Constructor without status should throw exception.");
    }
    @Test
    public void msgCauseNoStatus() {
        assertThrows(UnsupportedOperationException.class,
                () -> new ServerStatusException(MSG, CAUSE),
                "Constructor without status should throw exception.");
    }
    @Test
    public void causeNoStatus() {
        assertThrows(UnsupportedOperationException.class,
                () -> new ServerStatusException(CAUSE),
                "Constructor without status should throw exception.");
    }
    @Test
    public void status() {
        assertTrue(new ServerStatusException(STATUS).getHttpStatusCode()
                        == STATUS,
                "Initialisation failed.");
    }
    @Test
    public void msgStatus() {
        ServerStatusException exception =
                new ServerStatusException(MSG, STATUS);
        assertTrue(exception.getHttpStatusCode() == STATUS,
                "Initialisation failed.");
        assertTrue(exception.getMessage().equals(MSG),
                "Initialisation failed.");
    }
    @Test
    public void msgStatusCause() {
        ServerStatusException exception =
                new ServerStatusException(MSG, STATUS, CAUSE);
        assertTrue(exception.getHttpStatusCode() == STATUS,
                "Initialisation failed.");
        assertTrue(exception.getMessage().equals(MSG),
                "Initialisation failed.");
        assertTrue(exception.getCause().equals(CAUSE),
                "Initialisation failed.");
    }
    @Test
    public void statusCause() {
        ServerStatusException exception =
                new ServerStatusException(STATUS, CAUSE);
        assertTrue(exception.getHttpStatusCode() == STATUS,
                "Initialisation failed.");
        assertTrue(exception.getCause().equals(CAUSE),
                "Initialisation failed.");
    }
    @Test
    public void getHttpStatusCode() {
        assertTrue(new ServerStatusException(STATUS).getHttpStatusCode()
        == STATUS, "Status codes don't match.");
    }
}
