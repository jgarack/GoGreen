package server;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utility.AccountMessage;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private List<String> users = new ArrayList<String>();
    private List<String> passes = new ArrayList<String>();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @PostMapping("/login")
    public ResponseEntity loginResponse(
            @RequestBody AccountMessage account) {

        boolean userExists = users.contains(account.getUsername());
        boolean passAccepted = passes.get(users.indexOf(account.getUsername())).equals(account.getPassword());
        if(userExists && passAccepted){
            return new ResponseEntity("Hello " + account.getUsername() + " Authenticated!", HttpStatus.OK);
        }else{
            return new ResponseEntity("Unknown user-password combination.", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity registerResponse(
        @RequestBody AccountMessage account){
        if(!users.contains(account.getUsername()) && users.size() == passes.size()){
            users.add(account.getUsername());
            passes.add(account.getPassword());
            return new ResponseEntity("Account created for user: " + account.getUsername(), HttpStatus.OK);
        }else if(users.contains(account.getUsername())){
            return new ResponseEntity("Chosen username is already taken.", HttpStatus.CONFLICT);
        }
        return new ResponseEntity("Your account could not be created", HttpStatus.BAD_REQUEST);
    }
}