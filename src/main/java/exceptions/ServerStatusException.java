package exceptions;

/**
 * Checked exception that should be thrown when the server responds with an
 * unexpected status code.
 * @author awjvanvugt
 */
public class ServerStatusException extends Exception {
    /**
     * Message for the unchecked exception thrown in a constructor.
     * {@value}
     */
    private static final String UNSUPPORTED = "ServerStatusException should"
            + "have a status code attached";

    /**
     * Template for the message to use when instantiating this exception type.
     * {@value}
     */
    private static final String TEMPLATE =
            "Server returned status code: %d%n%s";

    /**
     *  The status code returned by the server.
     */
    private int httpStatusCode;

    //override parent constructors to throw an unchecked exception

    /**
     * Override super constructor to throw an unchecked exception if no status
     * code is specified.
     * @throws UnsupportedOperationException when called
     */
    public ServerStatusException() {
        super();
        throw new UnsupportedOperationException(UNSUPPORTED, this);
    }

    /**
     * Override super constructor to throw an unchecked exception if no status
     * code is specified.
     * @param message A descriptive message about the exception.
     * @throws UnsupportedOperationException when called
     */
    public ServerStatusException(final String message) {
        super(message);
        throw new UnsupportedOperationException(UNSUPPORTED, this);
    }

    /**
     * Override super constructor to throw an unchecked exception if no status
     * code is specified.
     * @param message A descriptive message about the exception.
     * @param cause The cause of the exception.
     * @throws UnsupportedOperationException when called
     */
    public ServerStatusException(final String message, final Throwable cause) {
        super(message, cause);
        throw new UnsupportedOperationException(UNSUPPORTED, this);
    }

    /**
     * Override super constructor to throw an unchecked exception if no status
     * code is specified.
     * @param cause The cause of the exception.
     * @throws UnsupportedOperationException when called
     */
    public ServerStatusException(final Throwable cause) {
        super(cause);
        throw new UnsupportedOperationException(UNSUPPORTED, this);
    }

    //constructors to construct a proper instance of this object

    /**
     * Initialise a new ServerStatusException.
     * @param statusCode The status code returned by the server.
     */
    public ServerStatusException(final int statusCode) {
        super(String.format(TEMPLATE, statusCode, ""));
        httpStatusCode = statusCode;
    }

    /**
     * Initialise a new ServerStatusException.
     * @param message A descriptive message about the exception.
     * @param statusCode The status code returned by the server.
     */
    public ServerStatusException(final String message, final int statusCode) {
        super(String.format(TEMPLATE, statusCode, message));
        httpStatusCode = statusCode;
    }

    /**
     * Initialise a new ServerStatusException.
     * @param message A descriptive message about the exception.
     * @param statusCode The status code returned by the server.
     * @param cause The cause of the exception.
     */
    public ServerStatusException(final String message, final int statusCode,
                                 final Throwable cause) {
        super(String.format(TEMPLATE, statusCode, message), cause);
        httpStatusCode = statusCode;
    }

    /**
     * Initialise a new ServerStatusException.
     * @param statusCode The status code returned by the server
     * @param cause The cause of the exception.
     */
    public ServerStatusException(final int statusCode, final Throwable cause) {
        super(String.format(TEMPLATE, statusCode, ""), cause);
        httpStatusCode = statusCode;
    }

    /**
     * Getter for the private httpStatusCode field.
     * @return The status code assigned to this exception.
     */
    public int getHttpStatusCode() {
        return httpStatusCode;
    }
}
