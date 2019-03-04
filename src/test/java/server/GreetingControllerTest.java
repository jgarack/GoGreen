package server;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.TestInstance;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import resources.AbstractTest;
import utility.AccountMessage;
import utility.Activity;
import utility.Authenticator;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)

// TODO:
//    @Test
//    public void internalSeverError() throws Exception

/**
 * The class for testing GreetingController.
 * @author Vidas
 */
public class GreetingControllerTest extends AbstractTest
{
    @Override
    @BeforeAll
    public void setUp() {
        super.setUp();
    }

    /**
     * Sends the server a login request without registering the user first.
     * @throws Exception
     */
    @Test
    public void loginResponseTestUnauthorized() throws Exception
    {

        String uri = "/login";
        AccountMessage account = new AccountMessage("username", "password");
        String inputJson = super.mapToJson(account);
        System.out.println(inputJson);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(401, status);
    }

    /**
     * Registers the user and then sends the server a login request.
     * @throws Exception
     */
    @Test
    public void loginResponseTestAuthorized() throws Exception
    {
        String uri = "/register";
        AccountMessage account = new AccountMessage("username", "password");
        String inputJson = super.mapToJson(account);
        mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson));

        String url = "/login";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    /**
     * Registers a user.
     * @throws Exception
     */
    @Test
    public void registerTest() throws Exception
    {

        String uri = "/register";
        AccountMessage account = new AccountMessage("username", "password");
        String inputJson = super.mapToJson(account);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    /**
     * Tries registering the same user twice.
     * @throws Exception
     */
    @Test
    public void registerTestTwice() throws Exception
    {

        String uri = "/register";
        AccountMessage account = new AccountMessage("username", "password");
        String inputJson = super.mapToJson(account);
        mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson));

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(409, status);
    }

    /**
     * Tests an activity with ID of 1.
     * @throws Exception
     */
    @Test
    public void pointsTest() throws Exception
    {

        String uri = "/points";
        Activity activity = new Activity(1,5 );

        String inputJson = super.mapToJson(activity);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    /**
     * Tests an activity with an ID of 3 ("Not an activity").
     * @throws Exception
     */
    @Test
    public void pointsTestNotAnActivity() throws Exception
    {

        String uri = "/points";
        Activity activity = new Activity(3,5 );

        String inputJson = super.mapToJson(activity);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }





}
