package utility;

import javafx.application.Application;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import server.GreetingController;
import sun.rmi.runtime.Log;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootApplication
@ComponentScan(basePackageClasses = GreetingController.class)
public class LoginHandlerTest extends SpringBootServletInitializer {
    private static final String NULL = "";
    private static final String USER = "user";
    private static final String PASS = "pass";
    private static final String ADDITION = "123";
    private static final String PASS_TOMD5 = "1a1dc91c907325c69271ddf0c944bc72";

    @BeforeEach
    public void testServer(){
        SpringApplication.run(Application.class, new String[] {});
    }

    @Test
    public void registerUserNull() {
        assertFalse(LoginHandler.registerSubmit(NULL, PASS, PASS),
                "Handler allowed register without username.");
    }
    @Test
    public void registerPassNull() {
        assertFalse(LoginHandler.registerSubmit(USER, NULL, NULL),
                "Handler allowed register without password.");
    }
    @Test
    public void registerEmptySucceed() {
        assertTrue(LoginHandler.registerSubmit(USER, PASS, PASS),
                "Handler rejected valid registration.");
    }
    @Test
    public void registerSucceed() {
        LoginHandler.registerSubmit(USER, PASS, PASS);
        assertFalse(LoginHandler.registerSubmit(USER+ADDITION, PASS, PASS),
                "Handler rejected unique registration.");
    }
    @Test
    public void registerFailure(){
        LoginHandler.registerSubmit(USER, PASS, PASS);
        assertFalse(LoginHandler.registerSubmit(USER, PASS, PASS),
                "Handler accepted duplicate registration.");
    }
    @Test
    public void loginUserNull() {
        assertFalse(LoginHandler.loginSubmit(NULL, PASS),
                "Handler allowed login without username.");
    }
    @Test
    public void loginPassNull() {
        assertFalse(LoginHandler.loginSubmit(USER, NULL),
                "Hanlder allowed login without password.");
    }
    @Test
    public void loginEmptyFailure() {
        assertFalse(LoginHandler.loginSubmit(USER, PASS),
                "Handler allowed login while no accounts are registered.");
    }
    @Test
    public void loginUserFailure() {
        LoginHandler.registerSubmit(USER, PASS, PASS);
        assertFalse(LoginHandler.loginSubmit(USER+ADDITION, PASS),
                "Handler allowed non-existing user to log in.");
    }
    @Test
    public void loginPassFailure(){
        LoginHandler.registerSubmit(USER, PASS, PASS);
        assertFalse(LoginHandler.loginSubmit(USER, PASS+ADDITION));
    }
    @Test
    public void loginSucceed() {
        LoginHandler.registerSubmit(USER, PASS, PASS);
        assertTrue(LoginHandler.loginSubmit(USER, PASS),
                "Valid login credentials were rejected.");
    }
}
