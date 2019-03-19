package features;


import javafx.scene.control.Alert;
import utility.HttpRequestHandler;

import java.io.BufferedReader;


/**
 * Performs a http post request to server to
 * calculate points for vegetarian meal.
 */

public class Feature {
    /**
     * Server Domain.
     */
    private final String domain = "http://localhost:8080";
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
     */
    public Feature(final int userValue, final int choice) {
        this.value = userValue;
        this.id = choice;
    }
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
////        checkForm();
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
//        }
//        return 0;
//    }


    /**
     * Helper method that handles exceptions.
     *
     * @param e exception that was thrown
     */
    protected void exceptionHandler(final Exception e) {
        Alert statusCodeError = new Alert(Alert.AlertType.ERROR);
        statusCodeError.setTitle(e.getMessage());
//        statusCodeError.setContentText("See terminal for stacktrace.");
        statusCodeError.setContentText(e.toString());
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
