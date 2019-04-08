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
     * Number.
     * {@value}
     */
    private static final int SIXTY = 60;
    /**
     * Number.
     * {@value}
     */
    private static final double THREE_POINT_SIX = 3.6;
    /**
     * Number.
     * {@value}
     */
    private static final int EIGHTYEIGHT = 88;
    /**
     * Number.
     * {@value}
     */
    private static final int THREE = 3;
    /**
     * Number.
     * {@value}
     */
    private static final int FOUR = 4;
    /**
     * Number.
     * {@value}
     */
    private static final int FIVE = 5;
    /**
     * Number.
     * {@value}
     */
    private static final int SIX = 6;
    /**
     * Number.
     * {@value}
     */
    private static final int ONEHUNDREDANDTEN = 110;
    /**
     * Number.
     * {@value}
     */
    private static final int ONETHOUSAND = 1000;

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
    public HttpRequestHandler httpHandler =
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
        System.out.println(request.getUsername() + request.getActivityID()
                + request.getAmount());
        String username = request.getUsername();
        int amount = request.getAmount();
        System.out.println("amount on server:" + amount);
        int activityID = request.getActivityID();
        //veg meal
        if (activityID == Integer.parseInt("1")) {

            BufferedReader httpBody =
                    httpHandler.reqGet("/diets."
                            + "json?size="
                            + amount
                            + "&timeframe=2019-01-01%2F2019-01-02"
                            + BP_KEY);
            BufferedReader veg =
                    httpHandler.reqGet("/diets."
                            + "json?size="
                            + amount
                            + "&diet_class=vegetarian"
                            + "&timeframe=2019-01-01%2F2019-01-02"
                            + BP_KEY);
            if (!dbAdaptor.getAchievements(
                    request.getUsername()).contains(5)) {

                if (dbAdaptor.getPerformedTimes(
                        request.getUsername(), 1) >= 4) {
                    dbAdaptor.addAchievement(5, request.getUsername());
                }
            }
            if (!dbAdaptor.getAchievements(
                    request.getUsername()).contains(6)) {

                if (dbAdaptor.getPerformedTimes(
                        request.getUsername(), 1) >= 5) {
                    dbAdaptor.addAchievement(6, request.getUsername());
                }

            }
            amount = jsonCon(HttpRequestHandler.resLog(httpBody, null))
                    - jsonCon(HttpRequestHandler.resLog(veg, null));

        } else if (activityID == Integer.parseInt("2")) {
            //bicycle
            BufferedReader httpBody =
                    httpHandler.reqGet("/automobile_"
                            + "trips.json?duration=" + amount * SIXTY
                            + BP_KEY);
            if (!dbAdaptor.getAchievements(
                    request.getUsername()).contains(1)) {
                if (dbAdaptor.getPerformedTimes(
                        request.getUsername(), 2) >= 4) {
                    dbAdaptor.addAchievement(1, request.getUsername());
                }
            }
            if (!dbAdaptor.getAchievements(
                    request.getUsername()).contains(2)) {

                if (dbAdaptor.getPerformedTimes(
                        request.getUsername(), 2) >= 49) {
                    dbAdaptor.addAchievement(2, request.getUsername());
                }
            }

            amount = jsonCon(HttpRequestHandler.resLog(httpBody, null));

        } else if (activityID == THREE) {
            //local produce
            amount = amount * EIGHTYEIGHT;

        } else if (activityID == FOUR) {
            //public transport

            BufferedReader httpBody =
                    httpHandler.reqGet("/bus_"
                            + "trips.json?duration=" + amount * SIXTY
                            + BP_KEY);
            BufferedReader car =
                    httpHandler.reqGet("/automobile_"
                            + "trips.json?duration=" + amount * SIXTY
                            + BP_KEY);
            if (!dbAdaptor.getAchievements(
                    request.getUsername()).contains(3)) {

                if (dbAdaptor.getPerformedTimes(
                        request.getUsername(), FOUR) >= 4) {
                    dbAdaptor.addAchievement(3, request.getUsername());
                }
            }
            if (!dbAdaptor.getAchievements(
                    request.getUsername()).contains(4)) {

                if (dbAdaptor.getPerformedTimes(
                        request.getUsername(), FOUR) >= 49) {
                    dbAdaptor.addAchievement(4, request.getUsername());
                }
            }
            amount = jsonCon(HttpRequestHandler.resLog(car, null))
                    - jsonCon(HttpRequestHandler.resLog(httpBody, null));

        } else if (activityID == FIVE) {
            //solar panels
            if (dbAdaptor.getDate(request.getUsername()) != null) {
                ZoneId zone = ZoneId.of("Europe/Amsterdam");
                LocalDate today = LocalDate.now(zone);
                java.util.Date conv = new java.util.Date(dbAdaptor.getDate(
                        request.getUsername()).getTime());
                LocalDate lastAdded =
                        conv.toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate();
                LocalDate oneMonthLater = lastAdded
                        .plusMonths(Integer.parseInt("1"));

                if (oneMonthLater.getMonthValue() > today.getMonthValue()) {
                    System.out.println("you are here");
                    return new ResponseEntity("false", HttpStatus.OK);
                }
            } else {
                if (!dbAdaptor.getAchievements(
                        request.getUsername()).contains(7)) {

                    dbAdaptor.addAchievement(7, request.getUsername());
                }
                if (!dbAdaptor.getAchievements(
                        request.getUsername()).contains(8)) {

                    if (dbAdaptor.getPerformedTimes(
                            request.getUsername(), FIVE) >= 2) {
                        dbAdaptor.addAchievement(8, request.getUsername());
                    }
                }
                BufferedReader httpBody =
                        httpHandler.reqGet("/electricity_uses.json?"
                                + "energy=" + (double) amount / THREE_POINT_SIX
                                + "&timeframe=2019-01-01%2F2019-02-01"
                                + BP_KEY);

                Date today = new Date(System.currentTimeMillis());
                dbAdaptor.updateDate(request.getUsername(), today);
                amount = jsonCon(HttpRequestHandler.resLog(httpBody, null));
            }
        } else if (activityID == SIX) {
            //reducing home temperature according to data from
            // https://www.epa.gov/environmental-economics
            // /environmental-economics-research-strategy

            if (!dbAdaptor.getAchievements(request.getUsername()).contains(9)) {
                if (amount == 5) {
                    dbAdaptor.addAchievement(9,
                            request.getUsername());
                }
            }

            if (!dbAdaptor.getAchievements(request.getUsername()).contains(10)) {
                if (amount == 10) {
                    dbAdaptor.addAchievement(10,
                            request.getUsername());
                    if (!dbAdaptor.getAchievements(
                            request.getUsername()).contains(9)) {

                        dbAdaptor.addAchievement(9,
                                request.getUsername());
                    }
                }
            }
            amount = amount * ONEHUNDREDANDTEN;

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
        //todo: add achievements
        //        if(dbAdaptor.getTotalScore(username) >= 1000000
        //                && !dbAdaptor.getAchievements(username).contains(12)){
        //            dbAdaptor.addAchievement(12,username);
        //        }
        //        if(dbAdaptor.getFriends(username).size()>=10
        //                && !dbAdaptor.getAchievements(username).contains(11)){
        //            dbAdaptor.addAchievement(11,username);
        //        }
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
                .get("carbon").get("object").get("value").asDouble()
                * ONETHOUSAND;
        //multiply by 1000 to convert to grams
        return ret.intValue();
    }
}
