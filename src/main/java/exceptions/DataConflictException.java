package exceptions;

/**
 * Unchecked Exception that should be thrown when the back-end data structure
 * can't handle the input.
 */
public class DataConflictException extends Exception {
    /**
     * Initialises a new DataConflictException.
     */
    public DataConflictException() {
        super();
    }

    /**
     * Initialises a new DataConflictException.
     * @param message A descriptive message about the exception.
     */
    public DataConflictException(final String message) {
        super(message);
    }

    /**
     * Initialises a new DataConflictException.
     * @param message A descriptive message about the exception.
     * @param cause The cause of the exception.
     */
    public DataConflictException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Initialises a new DataConflictException.
     * @param cause The cause of the exception.
     */
    public DataConflictException(final Throwable cause) {
        super(cause);
    }
}
