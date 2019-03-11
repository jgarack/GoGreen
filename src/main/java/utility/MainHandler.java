package utility;

/**
 * Handler for the main controller.
 */
public final class MainHandler {
    /**
     * Literally does nothing.
     */
    private MainHandler() { }
    /**
     * Tries to parse integer.
     *
     * @param value String that is to be parsed.
     * @return true iff the value provided is actual representation of integer.
     */
    public static boolean tryParseInt(final String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
