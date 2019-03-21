package server;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
//native imports
import utility.DbAdaptor;
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
     * DB_ADAPTOR connections/ disconnection/ authentication.
     */
    private static final DbAdaptor DB_ADAPTOR = new DbAdaptor();

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
        if (DB_ADAPTOR.comparecredentials(account)) {
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
     * @param regCre The register credentials of the account to create.
     *                Send by the client in JSON form.
     * @return An HTTP response as a ResponseEntity Object.
     */
    @PostMapping("/register")
    public ResponseEntity registerResponse(
        @RequestBody final RegisterCredentials regCre) {

        if (DB_ADAPTOR.addNewUser(regCre)) {
            return new ResponseEntity("Registration successful. "
                    + "You can now log in", HttpStatus.OK);
        }
        return new ResponseEntity("Your account could not be created",
                    HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
