package utility;

/**
 * Object to contain a message, and an id for identification.
 */
public class Greeting {
    /**
     * The unique id of this message.
     */
    private final long id;

    /**
     * The message contained by this Object.
     */
    private final String content;

    /**
     * Constructs a Greeting.
     * @param uniqueId the unique id for this Object.
     * @param message the message to contain.
     */
    public Greeting(final long uniqueId, final String message) {
        this.id = uniqueId;

        this.content = message;
    }

    /**
     * Getter method for id.
     * @return id
     * @see #id
     */
    public long getId() {
        return id;
    }

    /**
     * Getter method for the message contained.
     * @return content
     * @see #content
     */
    public String getContent() {
        return content;
    }
}

