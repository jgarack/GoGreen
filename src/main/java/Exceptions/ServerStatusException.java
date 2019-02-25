package Exceptions;

public class ServerStatusException extends Exception {
    /** Message for the unchecked exception thrown in a constructor.**/
    private final String UNSUPPORTED = "ServerStatusException should have a "
            + "status code attached";
    /** Template for the message to use when instantiating this exception type.
    **/
    private static final String TEMPLATE = "Server returned status code: %d%n%s"
            ;
    private int httpStatusCode;

    //override parent constructors to throw an unchecked exception
    public ServerStatusException() {
        super();
        throw new UnsupportedOperationException(UNSUPPORTED);
    }
    public ServerStatusException(final String message){
        super(message);
        throw new UnsupportedOperationException(UNSUPPORTED);
    }
    public ServerStatusException(final String message, final Throwable cause) {
        super(message, cause);
        throw new UnsupportedOperationException(UNSUPPORTED);
    }
    public ServerStatusException(final Throwable cause){
        super(cause);
        throw new UnsupportedOperationException(UNSUPPORTED);
    }
    //constructors to construct a proper instance of this object
    public ServerStatusException(final int statusCode){
        super(String.format(TEMPLATE, statusCode));
        httpStatusCode = statusCode;
    }
    public ServerStatusException(final String message, final int statusCode) {
        super(String.format(TEMPLATE, statusCode, message));
        httpStatusCode = statusCode;
    }
    public ServerStatusException(final String message, final int statusCode,
                                 final Throwable cause){
        super(String.format(TEMPLATE, statusCode, message), cause);
        httpStatusCode = statusCode;
    }
    public ServerStatusException(final int statusCode, final Throwable cause){
        super(String.format(TEMPLATE, statusCode), cause);
        httpStatusCode = statusCode;
    }

    public int getHttpStatusCode(){
        return httpStatusCode;
    }
}