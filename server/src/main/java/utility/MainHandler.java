package utility;

import exceptions.ServerStatusException;
import gui.AlertBuilder;

import java.io.IOException;

/**
 * Handler for the main controller.
 */
public class MainHandler {

    /**
     * A private Handler for Http requests.
     */
    private HttpRequestHandler httpHandler;
    /**
     * Username of the user.
     */
    public static String username;
    /**
     * Constructor.
     * @param domain The host.
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

    /**
     * Updates a veg meal.
     * @param amount to be updated.
     * @return returns the new amount.
     */
    public int updateVegMeal(final int amount) {
        try {
            return Integer.parseInt(httpHandler

                    .reqPost("/points",
                            new UpdateRequest(username, 1, amount))
                    .readLine());
        } catch (IOException | ServerStatusException e) {
            new AlertBuilder().displayException(e);
            return -1;
        }
    }
    public int updateBike(final int amount) {
        try {
            return Integer.parseInt(httpHandler

                    .reqPost("/points",
                            new UpdateRequest(username, 2, amount))
                    .readLine());
        } catch (IOException | ServerStatusException e) {
            new AlertBuilder().displayException(e);
            return -1;
        }
    }

    /**
     * Gets the total score.
     * @return the total score.
     * @throws IOException When req fails.
     * @throws ServerStatusException When req fails.
     */
    public int getTotalScore() throws IOException, ServerStatusException {
        return Integer
                .parseInt(httpHandler
                        .reqPost("/total", username)
                        .readLine());
    }


}
