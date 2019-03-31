package utility;

import exceptions.ServerStatusException;
import gui.AlertBuilder;

import java.io.IOException;

/**
 * Handler for the main controller.
 */
public class MainHandler {
    /**
     * Username of the user.
     */
    public static String username;

    /**
     * A private Handler for Http requests.
     */
    private HttpRequestHandler httpHandler;

    private AlertBuilder alertBuilder;


    /**
     * Constructor.
     * @param domain The host.
     */
    public MainHandler(final String domain) {
        httpHandler = new HttpRequestHandler(domain);
        alertBuilder = new AlertBuilder();
    }

    public void setHttpHandler(HttpRequestHandler handler) {
        httpHandler = handler;
    }

    public HttpRequestHandler getHttpHandler() {
        return httpHandler;
    }

    public void setAlertBuilder(AlertBuilder builder) {
        alertBuilder = builder;
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

    /*
     * Checks if the subtracted value
     * is greater than the initial one.
     * @param initVal initial value
     * @param valSubtract value to be subtracted
     * @return true if the initial is greater or equal
     */
    /*public static boolean checkPositiveValues(final int initVal,
                                              final int valSubtract) {
        return initVal >= valSubtract;
    }*/

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

    /**
     * Updates Bike trip.
     * @param amount to be updated
     * @return returns the new amount
     */
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
     * Updates Local consumption.
     * @param amount to be updated
     * @return the new amount
     */
    public int updateLocal(final int amount) {
        try {
            return Integer.parseInt(httpHandler

                    .reqPost("/points",
                            new UpdateRequest(username, 3, amount))
                    .readLine());
        } catch (IOException | ServerStatusException e) {
            new AlertBuilder().displayException(e);
            return -1;
        }
    }
<<<<<<< HEAD
    /**
     * Updates bus consumption.
     * @param amount to be updated
     * @return the new amount
=======

    /**
     * updates Bus points.
     * @param amount which should be added
     * @return 0 if ok, -1 if exception thrown
>>>>>>> f3d82b33822227fdba0c8099d0d68ac603991d7e
     */
    public int updateBus(final int amount) {
        try {
            return Integer.parseInt(httpHandler

                    .reqPost("/points",
                            new UpdateRequest(username, 4, amount))
                    .readLine());
        } catch (IOException | ServerStatusException e) {
            new AlertBuilder().displayException(e);
            return -1;
        }
    }
<<<<<<< HEAD
    /**
     * Updates solar consumption.
     * @param amount to be updated
     * @return the new amount
=======

    /**
     * updates solar panels.
     * @param amount which should be added
     * @return 0 if ok, -1 if exception thrown
>>>>>>> f3d82b33822227fdba0c8099d0d68ac603991d7e
     */
    public int updateSolar(final int amount) {
        try {
            String res = httpHandler
                    .reqPost("/points",
                            new UpdateRequest(username, 5, amount))
                    .readLine();
            if (res.equals("false")) {
                System.out.println(res);
                alertBuilder.showInformationNotification(
                        "You have already "
                                + "performed this activity this month");
                return 0;
            }
            return Integer.parseInt(res);


        } catch (IOException | ServerStatusException e) {
            new AlertBuilder().displayException(e);
            return -1;
        }
    }public int updateHeating(final int amount) {
        try {
            String res = httpHandler

                    .reqPost("/points",
                            new UpdateRequest(username, 6, amount))
                    .readLine();
            return Integer.parseInt(res);


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
