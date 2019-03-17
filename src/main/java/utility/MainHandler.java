package utility;

import exceptions.ServerStatusException;
import gui.AlertBuilder;

import java.io.IOException;

/**
 * Handler for the main controller.
 */
public class MainHandler {

    private HttpRequestHandler httpHandler;
    public static String username;
    /**
     * Constructor.
     */
    public MainHandler(final String domain) {
        httpHandler = new HttpRequestHandler(domain);
    }
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

    public int updateVegMeal(int amount) {
        try {
            return Integer.parseInt(httpHandler
                    .reqPost("/vegmeal", new UpdateRequest(username, 1, amount)).readLine());
        } catch(Exception e) {
            new AlertBuilder().displayException(e);
            return -1;
        }
    }

    public int getTotalScore() throws IOException, ServerStatusException {
        return Integer.parseInt(httpHandler.reqPost("/total", username).readLine());
    }


}
