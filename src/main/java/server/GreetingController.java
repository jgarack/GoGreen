package server;

import java.io.BufferedReader;
import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
//native imports
import utility.Activity;
import utility.DbAdaptor;
import utility.HttpRequestHandler;
import utility.LoginCredentials;
import utility.RegisterCredentials;
import utility.UpdateRequest;

/**
 * Class that maps route requests made to the server.
 * @author Omar
 */
@RestController
public class GreetingController {
    /**
     * The template for a response message.
     * {@value}
     */
    private static final String TEMPLATE = "Hello, %s!";
    /**
     * Login page routing.
     */
    private static final String LOGIN_PAGE = "./login";
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
     * DB_ADAPTOR connections/ disconnection/ authentication.
     */
    private static final DbAdaptor DB_ADAPTOR = new DbAdaptor();
    /**
     * HttpRequestHandler object that can be used for contacting the api.
     */
    private static final HttpRequestHandler HTTP_HANDLER_API =
            new HttpRequestHandler(BP_API);

    /**
     * Default mapping for index.
     * @return ResponseEntity with status code and head
     */

    @GetMapping("/")
    public ResponseEntity indexRedirect() {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(LOGIN_PAGE));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    /**
     * Mapping for route /login. Takes an account object from the client and
     * performs authentication.
     * @param account The credentials sent by the client, retrieved from JSON.
     * @return HTTP response as a ResponseEntity Object.
     */
    @PostMapping("/login")
    public ResponseEntity loginResponse(
            @RequestBody final LoginCredentials account) {
        DB_ADAPTOR.connect();
        if (DB_ADAPTOR.comparecredentials(account)) {
            DB_ADAPTOR.disconnect();
            return new ResponseEntity("Hello " + account.getUsername()
                    + " Authenticated!", HttpStatus.OK);
        } else {
            DB_ADAPTOR.disconnect();
            return new ResponseEntity("Unknown user-password combination.",
                    HttpStatus.UNAUTHORIZED);
        }

    }

    /**
     * Mapping for route /login. Takes an account object from the client and
     * stores it in the Authenticator Object associated to this instance.
     * Sends a conflict error response to the client if the username is already
     * taken. Sends an internal server error response if the Authenticator
     * fails to create a new account.
     * @param regCre The register credentials of the account to create.
     *                Send by the client in JSON form.
     * @return An HTTP response as a ResponseEntity Object.
     */
    @PostMapping("/register")
    public ResponseEntity registerResponse(
        @RequestBody final RegisterCredentials regCre) {

        DB_ADAPTOR.connect();
        if (DB_ADAPTOR.addNewUser(regCre)) {
            DB_ADAPTOR.disconnect();
            return new ResponseEntity("Registration successful. "
                    + "You can now log in", HttpStatus.OK);
        }
        DB_ADAPTOR.disconnect();
        return new ResponseEntity("Your account could not be created",
                    HttpStatus.INTERNAL_SERVER_ERROR);

    }

    /**
     * Updates the vegetarian meals.
     * @param request Request to be updated.
     * @return ResponseEntity that updates the vegMeal
     */
    @PostMapping("/vegmeal")
    public ResponseEntity vegmealUpdate(
            @RequestBody final UpdateRequest request) {
        String username = request.getUsername();
        int amount = request.getAmount();
        int activityID = request.getActivityID();
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
    @GetMapping("/total")
    public ResponseEntity totalScore(@RequestBody final String username) {
        return new ResponseEntity(DB_ADAPTOR
                .getUser(username).getTotalScore(), HttpStatus.OK);
    }

    /**
     * Mapping for post request to calculate points.
     * @param activity Activity to be calculated
     * @return ResponseEntity with http response body and status code
     * @throws Exception UrlNotFound
     */
    @PostMapping("/points")
    public ResponseEntity pointsResponse(
            @RequestBody final Activity activity) throws Exception {
        if (activity.getId() == 1) {

            BufferedReader httpBody =
                    new HttpRequestHandler(BP_API).reqGet("/diets."
                            + "json?size="
                            + activity.getValue()
                            + "&timeframe=2019-03-01%2F2019-03-02"
                            + BP_KEY);
            return new ResponseEntity(HTTP_HANDLER_API
                    .resLog(httpBody, null),
                    HttpStatus.OK);
        }
        if (activity.getId() == 2) {
            BufferedReader httpBody =
                    new HttpRequestHandler(BP_API).reqGet("/automobile_"
                            + "trips.json?duration=" + activity.getValue()
                            + BP_KEY);
            return new ResponseEntity(HTTP_HANDLER_API
                    .resLog(httpBody, null),
                    HttpStatus.OK);
        }
        return new ResponseEntity("Not an Activity", HttpStatus.OK);
    }
}
