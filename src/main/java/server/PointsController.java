package server;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import features.Feature;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import utility.DbAdaptor;
import utility.HttpRequestHandler;
import java.io.BufferedReader;
import java.io.IOException;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import utility.UpdateRequest;


/**
 * Class that maps points route requests made to the server.
 * @author Omar
 */
@RestController
public class PointsController {

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
    public HttpRequestHandler HTTP_HANDLER_API =
            new HttpRequestHandler(BP_API);
    /**
     * DB_ADAPTOR connections/ disconnection/ authentication.
     */
    public DbAdaptor DB_ADAPTOR = new DbAdaptor();
    /**
     * Mapping for post request to calculate points.
     * @param request DB update request to be calculated
     * @return ResponseEntity with http response body and status code
     * @throws Exception UrlNotFound
     */
    @PostMapping("/points")
    public ResponseEntity pointsResponse(
            @RequestBody final UpdateRequest request)throws Exception {
        String username = request.getUsername();
        int amount = request.getAmount();
        System.out.println("amount on server:" + amount);
        int activityID = request.getActivityID();
        //veg meal
        if (activityID == 1) {

            BufferedReader httpBody =
                    HTTP_HANDLER_API.reqGet("/diets."
                            + "json?size="
                            + amount
                            + "&timeframe=2019-01-01%2F2019-01-02"
                            + BP_KEY);
            BufferedReader veg =
                    HTTP_HANDLER_API.reqGet("/diets."
                            + "json?size="
                            + amount
                            +"&diet_class=vegetarian"
                            +"&timeframe=2019-01-01%2F2019-01-02"
                            + BP_KEY);
            System.out.println("/diets."
                    + "json?size="
                    + amount
                    +"&diet_class=vegetarian"
                    +"&timeframe=2019-01-01%2F2019-01-02"
                    + BP_KEY);
            amount = (jsonCon(HttpRequestHandler.resLog(httpBody,null))
        - jsonCon(HttpRequestHandler.resLog(veg,null)));

        }else if (activityID == 2) {
            //bycicle

            BufferedReader httpBody =
                    HTTP_HANDLER_API.reqGet("/automobile_"
                            + "trips.json?duration=" + amount * 60
                            + BP_KEY);
            amount = jsonCon(HttpRequestHandler.resLog(httpBody,null));

        }else if (activityID == 3) {
            //local produce
            BufferedReader httpBody =
                    HTTP_HANDLER_API.reqGet("/automobile_"
                            + "trips.json?duration=" + amount
                            + BP_KEY);
            amount = jsonCon(HttpRequestHandler.resLog(httpBody,null));

        }else if (activityID == 4) {
            //public transport

            BufferedReader httpBody =
                    HTTP_HANDLER_API.reqGet("/bus_"
                            + "trips.json?duration=" + amount * 60
                            + BP_KEY);
            BufferedReader car =
                    HTTP_HANDLER_API.reqGet("/automobile_"
                            + "trips.json?duration=" + amount * 60
                            + BP_KEY);
            amount = jsonCon(HttpRequestHandler.resLog(car,null))
                    - jsonCon(HttpRequestHandler.resLog(httpBody,null));

        }else if (activityID == 4) {
            //public transport

            BufferedReader httpBody =
                    HTTP_HANDLER_API.reqGet("/electricity_uses.json?" +
                            "energy=" + amount * 60
                            + BP_KEY);

            amount = jsonCon(HttpRequestHandler.resLog(httpBody, null));
        }

            if (!DB_ADAPTOR.updateActivity(username, activityID, amount)) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(DB_ADAPTOR
                .getActivityAmount(username, activityID), HttpStatus.OK);
    }
    /**
     * Returns a response entity with the total score.
     * @param username The username of
     *                 the user that is
     *                 used to retrieve the score.
     * @return the total score of a user.
     */
    @PostMapping("/total")
    public ResponseEntity totalScore(@RequestBody final String username) {
        return new ResponseEntity(DB_ADAPTOR
                .getTotalScore(username
                        .replace('"', ' ').trim()),
                HttpStatus.OK);
    }

    /**
     * Helper method that parses con to a desired integer.
     *
     * @param con The given String
     * @return An integer that is derived from the string.
     * @throws IOException Throws an exception if the Mapping is not successful.
     */
    public static int jsonCon(final String con) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode em = mapper.readValue(con, JsonNode.class);
        Double ret = (em.get("decisions").
                get("carbon").
                get("description").get("object").get("value").asDouble()*1000);
                //multiply by 1000 to convert to grams
        return ret.intValue();
    }
}
