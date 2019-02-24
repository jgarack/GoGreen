package server;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utility.AccountMessage;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @PostMapping("/login")
    public ResponseEntity postController(
            @RequestBody AccountMessage account) {

        if(account.getPassword().equals("Admin")){
            return new ResponseEntity("Hello" + account.getUsername() + "Authenticated", HttpStatus.OK);
        }

        return new ResponseEntity("Hello" + account.getUsername() + "Authentication failed", HttpStatus.UNAUTHORIZED );
    }
}