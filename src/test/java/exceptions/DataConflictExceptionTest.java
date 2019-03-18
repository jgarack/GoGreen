package exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataConflictExceptionTest {
    private static final String msg = "hello";
    private static final Throwable throwable = new Exception();

    @Test
    public void defaultConstructor() {
        assertTrue(new DataConflictException() instanceof
                DataConflictException, "Not instantiated.");
    }
    @Test
    public void msgConstructor() {
        assertTrue(new DataConflictException(msg).getMessage().equals(msg),
                "message not assigned.");
    }
    @Test
    public void msgThrowConstructor() {
        DataConflictException exception = new DataConflictException(msg, throwable);
        assertTrue(exception.getMessage().equals(msg),
                "message not assigned");
        assertTrue(exception.getCause().equals(throwable),
                "cause not assigned");
    }
    @Test
    public void throwConstructor(){
        assertTrue(new DataConflictException(throwable).getCause()
                .equals(throwable), "cause not assigned");
    }
}
