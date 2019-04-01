package server;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import utility.DbAdaptor;
import utility.HttpRequestHandler;
import utility.UpdateRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

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
    public DbAdaptor dbAdaptor = new DbAdaptor();

    /**
     * Mapping for post request to calculate points.
     * @param request DB update request to be calculated
     * @return ResponseEntity with http response body and status code
     * @throws Exception UrlNotFound
     */
    @PostMapping("/points")
    public ResponseEntity pointsResponse(
            @RequestBody final UpdateRequest request)throws Exception {
        System.out.println(request.getUsername() + request.getActivityID() + request.getAmount());
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
                            + "&diet_class=vegetarian"
                            + "&timeframe=2019-01-01%2F2019-01-02"
                            + BP_KEY);
            amount = jsonCon(HttpRequestHandler.resLog(httpBody,null))
                - jsonCon(HttpRequestHandler.resLog(veg,null));

        } else if (activityID == 2) {
            //bicycle

            BufferedReader httpBody =
                    HTTP_HANDLER_API.reqGet("/automobile_"
                            + "trips.json?duration=" + amount * 60
                            + BP_KEY);
            amount = jsonCon(HttpRequestHandler.resLog(httpBody,null));

        } else if (activityID == 3) {
            //local produce
            amount = amount * 88;

        } else if (activityID == 4) {
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

        } else if (activityID == 5 ) {
            //solar panels
            if (dbAdaptor.getDate(request.getUsername()) != null) {
                ZoneId zone = ZoneId.of( "Europe/Amsterdam" );
                LocalDate today = LocalDate.now( zone );
                java.util.Date conv = new java.util.Date(dbAdaptor.getDate(
                        request.getUsername()).getTime());
                LocalDate lastAdded =
                        conv.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
                LocalDate oneMonthLater = lastAdded.plusMonths( 1 );

                if (oneMonthLater.getMonthValue() > today.getMonthValue()) {
                    System.out.println("you are here");
                    return new ResponseEntity("false", HttpStatus.OK);
                }
            } else {
                BufferedReader httpBody =
                        HTTP_HANDLER_API.reqGet("/electricity_uses.json?"
                                + "energy=" + (double) amount / 3.6
                                + "&timeframe=2019-01-01%2F2019-02-01"
                                + BP_KEY);

                Date today = new Date(System.currentTimeMillis());
                dbAdaptor.updateDate(request.getUsername(), today);
                amount = jsonCon(HttpRequestHandler.resLog(httpBody, null));
            }
        }else if(activityID == 6) {
            //reducing home temperature according to data from https://www.epa.gov/environmental-economics/environmental-economics-research-strategy

            if(!dbAdaptor.getAchievements(request.getUsername()).contains(9))
            if(amount == 5)dbAdaptor.addAchievement(9,
                    request.getUsername());

            if(!dbAdaptor.getAchievements(request.getUsername()).contains(10))
            if(amount == 10)dbAdaptor.addAchievement(10,
                    request.getUsername());

            amount = amount * 110;
        }

        if (!dbAdaptor.updateActivity(username, activityID, amount)) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(dbAdaptor
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
        return new ResponseEntity(dbAdaptor
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
        Double ret = em.get("decisions")
                .get("carbon").get("object").get("value").asDouble() * 1000;
        //multiply by 1000 to convert to grams
        return ret.intValue();
    }
}
