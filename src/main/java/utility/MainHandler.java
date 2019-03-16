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

    /**
     * Checks if the subtracted value
     * is greater than the initial one.
     * @param initVal initial value
     * @param valSubtract value to be subtracted
     * @return true if
     * the initial is greater or equal
     */
    public static boolean checkPositiveValues(final int initVal,
                                              final int valSubtract) {
        return initVal >= valSubtract;
    }


}
