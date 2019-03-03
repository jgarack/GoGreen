package server;

import java.io.BufferedReader;
//java library imports
//import java.util.concurrent.atomic.AtomicLong;
//spring imports
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
//native imports
import exceptions.DataConflictException;
import utility.AccountMessage;
import utility.Activity;
import utility.HttpRequestHandler;
import utility.Authenticator;

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
     * Authenticator Object that can be used to authenticate a user.
     * State of the Authenticator can not be preserved yet.
     */
    private Authenticator authenticator = new Authenticator();

    /**
     * Mapping for route /login. Takes an account object from the client and
     * performs authentication.
     * @param account The credentials sent by the client, retrieved from JSON.
     * @return HTTP response as a ResponseEntity Object.
     */
    @PostMapping("/login")
    public ResponseEntity loginResponse(
            @RequestBody final AccountMessage account) {
        if (authenticator.authenticate(account)) {
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
     * Sends a conflict error response to the clietn if the username is already
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
            if (authenticator.registerNewUser(account)) {
                return new ResponseEntity("Registration successful. "
                        + "You can now log in", HttpStatus.CONFLICT);
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
                    HttpRequestHandler.reqGet("https://api."
                            + "carbonintensity.org.uk/intensity");
            return new ResponseEntity("Your Carbon emissions are:"
                    + HttpRequestHandler.resLog(httpBody, null),
                    HttpStatus.OK);
        }
        return new ResponseEntity("Not an Activity", HttpStatus.OK);
    }
}
