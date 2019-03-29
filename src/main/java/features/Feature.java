package features;

import javafx.scene.control.Alert;

/**
 * Performs a http post request to server to
 * calculate points for vegetarian meal.
 */

public class Feature {
    /**
     * value to be calculated.
     */
    private int value;
    /**
     * Id to identify type of activity ex: 1 = Vegetarian meal.
     */
    private int id;

    /**
     * Constructor for Feature.
     *
     * @param userValue Text field value
     * @param choice the activity id
     */
    public Feature(final int userValue, final int choice) {
        this.value = userValue;
        this.id = choice;
    }

    /**
     * Getter for the activity id.
     * @return activity id
     */
    public int getId() {
        return id;
    }



    /**
     * Getter for value.
     *
     * @return raw value
     */
    public int getValue() {
        return value;
    }

    /**
     * Setter for value.
     *
     * @param raw value without calculation
     */
    public void setValue(final int raw) {
        this.value = raw;
    }


    /**
     * Handles request for Vegetarian meal points from server.
     * Feature 1 VeggieMeal
     * Feature 2 BikeRide
     * @return returns points
     * @author ohussein
     */
    //    public int calculatePoints() {
    //
    //       checkForm();
    //        try {
    //            BufferedReader httpBody;
    //            httpBody = new HttpRequestHandler(domain).reqPost(
    //                    "/points", this);
    //
    //
    //            String con = new HttpRequestHandler(domain).resLog(
    //                    httpBody, null);
    //            System.out.println(con);
    //            return Integer.parseInt(con);
    //
    //        } catch (Exception e) {
    //
    //            exceptionHandler(e);
    //
    //    }
    //    return 0;
    //}


    /**
     * Helper method that handles exceptions.
     *
     * @param exception exception that was thrown
     */
    protected void exceptionHandler(final Exception exception) {
        Alert statusCodeError = new Alert(Alert.AlertType.ERROR);
        statusCodeError.setTitle(exception.getMessage());
        //statusCodeError.setContentText("See terminal for stacktrace.");
        statusCodeError.setContentText(exception.toString());
        statusCodeError.showAndWait();
    }

    /**
     * Converts object to a String representation.
     *
     * @return String representation of object
     */
    @Override
    public String toString() {
        return "Feature{"
                + "value=" + value
                + '}';
    }
}
