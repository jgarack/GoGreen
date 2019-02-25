package server;

import java.util.concurrent.atomic.AtomicLong;

import exceptions.DataConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utility.AccountMessage;
import utility.Authenticator;

@RestController
public class GreetingController {

    private final String TEMPLATE = "Hello, %s!";
    private final AtomicLong COUNTER = new AtomicLong();
    private Authenticator authenticator = new Authenticator();

    @PostMapping("/login")
    public ResponseEntity loginResponse(
            @RequestBody AccountMessage account) {
        if(authenticator.authenticate(account)){
            return new ResponseEntity("Hello " + account.getUsername()
                    + " Authenticated!", HttpStatus.OK);
        }else{
            return new ResponseEntity("Unknown user-password combination.",
                    HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity registerResponse(
        @RequestBody AccountMessage account) {
        try {
            if (authenticator.registerNewUser(account))
                return new ResponseEntity("Registration succesfull. "
                        + "You can now log in", HttpStatus.CONFLICT);
            return new ResponseEntity("Your account could not be created",
                    HttpStatus.BAD_REQUEST);
        } catch (DataConflictException taken) {
            return new ResponseEntity("Chosen username is already taken.",
                    HttpStatus.CONFLICT);
        }
    }
}