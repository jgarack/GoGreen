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
import exceptions.DataConflictException;
import utility.Authenticator;
import utility.AccountMessage;
import utility.HttpRequestHandler;
import utility.Activity;

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
     * Authenticator Object that can be used to authenticate a user.
     * State of the Authenticator can not be preserved yet.
     */
    private static final Authenticator AUTHENTICATOR = new Authenticator();
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
            @RequestBody final AccountMessage account) {
        if (AUTHENTICATOR.authenticate(account)) {
            return new ResponseEntity("Hello " + account.getUsername()
                    + " Authenticated!", HttpStatus.OK);
        } else {
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
     * @param account The login credentials of the account to create.
     *                Send by the client in JSON form.
     * @return An HTTP response as a ResponseEntity Object.
     */
    @PostMapping("/register")
    public ResponseEntity registerResponse(
        @RequestBody final AccountMessage account) {
        try {
            if (AUTHENTICATOR.registerNewUser(account)) {
                return new ResponseEntity("Registration successful. "
                        + "You can now log in", HttpStatus.OK);
            }
            return new ResponseEntity("Your account could not be created",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (DataConflictException taken) {
            return new ResponseEntity("Chosen username is already taken.",
                    HttpStatus.CONFLICT);
        }
    }

    /**
     * Mapping for post request to calculate points.
     * @param activity Activity to be calculated
     * @return ResponseEntity with http response body and status code
     * @throws Exception UrlNotFound
     */
    @PostMapping("/points")
    public ResponseEntity pointsResponse(
            @RequestBody final Activity activity)throws Exception {
        if (activity.getId() == 1) {

            BufferedReader httpBody =
                    HTTP_HANDLER_API.reqGet("/diets.json?size="
                            + activity.getValue()
                            //+ "&timeframe=2019-01-01%2F2020-01-01"
                            + BP_KEY);

            return new ResponseEntity(HTTP_HANDLER_API
                    .resLog(httpBody, null),
                    HttpStatus.OK);
        }
        return new ResponseEntity("Not an Activity", HttpStatus.OK);
    }
}
