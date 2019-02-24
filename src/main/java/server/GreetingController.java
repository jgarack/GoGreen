package server;

//import java.util.concurrent.atomic.AtomicLong;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

//    private static final String template = "Hello, %s!";
//    private final AtomicLong counter = new AtomicLong();

    @PostMapping("/login")
    public ResponseEntity postController(
            @RequestBody utility.AccountMessage account) {

        if(account.getPassword().equals("Admin")){
            return new ResponseEntity("Hello" + account.getUsername() + "Authenticated", HttpStatus.OK);
        }

        return new ResponseEntity("Hello" + account.getUsername() + "Authenticated", HttpStatus.UNAUTHORIZED );
    }
}