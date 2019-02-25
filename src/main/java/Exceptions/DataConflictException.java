package Exceptions;

public class DataConflictException extends Exception {
    public DataConflictException() {
        super();
    }
    public DataConflictException(final String message) {
        super(message);
    }
    public DataConflictException(final String message, final Throwable cause){
        super(message, cause);
    }
    public DataConflictException(final Throwable cause){
        super(cause);
    }
}
