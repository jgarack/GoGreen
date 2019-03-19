package features;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.ServerStatusException;
import gui.AlertBuilder;
import javafx.scene.control.Alert;
import utility.Activity;
import utility.HttpRequestHandler;

import java.io.BufferedReader;
import java.io.IOException;

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
     * Magic number 60.
     */
    private static final int SIXTY = 60;
    /**
     * Multiplier for the score of the user.
     */
    private int pointMultiplier;


    /**
     * api path.
     */
    private static final String BP_API = "http://impact.brighter"
            + "planet.com";
    /**
     * api key.
     */
    private static final String BP_KEY =
            "&key=5a98005a-09ff-4823-8d5b-96a3bbf3d7fd";

    /**
     * HttpRequestHandler object that can be used for contacting the api.
     */
    private static final HttpRequestHandler HTTP_HANDLER_API =
            new HttpRequestHandler(BP_API);

    /**
     * Constructor for Feature.
     *
     * @param userValue Text field value
     */
    public Feature(final String userValue) {
        this.value =
                Integer.parseInt(userValue.trim());
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
     * The builder used to build alerts for this handler.
     */
    private static final AlertBuilder ALERT_BUILDER = new AlertBuilder();


    /**

     * Handles request for Vegetarian meal points from server.
     * Feature 1 VeggieMeal
     * Feature 2 BikeRide
     * @return returns points
     * @author ohussein
     */

    //To add more features, add another if-else statement
    // with appropriate choice ID and pointMultiplier.
//    public int calculatePoints(final int choice) {
//        try {
//            BufferedReader httpBody;
//
//            //Picking Vegetarian Meal
//            if (choice == 1) {
//                httpBody = new HttpRequestHandler(
//                        domain).reqPost(
//                        "/points", new Activity(choice, this.getValue()));
//            } /*Picking Bike Ride*/ else if (choice == 2) {
//                pointMultiplier = SIXTY;
//                httpBody = new HttpRequestHandler(
//                        domain).reqPost(
//                        "/points", new Activity(choice, this.getValue()
//                                * pointMultiplier));
//            } else {
//                httpBody = new HttpRequestHandler(
//                        domain).reqPost(
//                        "/points", new Activity(choice, this.getValue()));
//            }
//
//
//            String con = new HttpRequestHandler(domain).resLog(
//                    httpBody, null);
//            System.out.println(con);
//            return jsonCon(con);
//
//        } catch (Exception e) {
//
//            exceptionHandler(e);
//
//        }
//        return 0;
//    }


//    /**
//     * Helper method that parses con to a desired integer.
//     *
//     * @param con The given String
//     * @return An integer that is derived from the string.
//     * @throws IOException Throws an exception if the Mapping is not succesful.
//     */
    public int jsonCon(final String con) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode em = mapper.readValue(con, JsonNode.class);
        int ret = (int) Math.ceil(Double.parseDouble(em.get("decisions").
                get("carbon").
                get("description").textValue().
                replace("kg", "").trim()));
        return ret;
    }

//    /**
//     * Helper method that handles exceptions.
//     *
//     * @param e exception that was thrown
//     */
//    protected void exceptionHandler(final Exception e) {
//        Alert statusCodeError = new Alert(Alert.AlertType.ERROR);
//        statusCodeError.setTitle(e.getMessage());
////        statusCodeError.setContentText("See terminal for stacktrace.");
//        statusCodeError.setContentText(e.toString());
//        statusCodeError.showAndWait();
//    }

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

    /**
     * Mapping for post request to calculate points.
     * @param amount Activity to be calculated
     * @return ResponseEntity with http response body and status code
     * @throws Exception UrlNotFound
     */
    public int vegmeal_calcScore(final int amount) {
        try {
            StringBuilder urlRouting = new StringBuilder("/diets.json?size=")
                    .append(amount).append("&timeframe=2019-01-01%2F2019-01-02")
                    .append(BP_KEY);
            BufferedReader httpBody =
                    HTTP_HANDLER_API.reqGet(urlRouting.toString());
            int res = jsonCon(HTTP_HANDLER_API.resLog(httpBody, null));
            System.out.println("calc res " + res);
            return res;
        } catch (IOException | ServerStatusException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
