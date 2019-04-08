package server;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import resources.AbstractTest;
import utility.AccountMessage;
import utility.Activity;
import utility.DbAdaptor;
import utility.RegisterCredentials;

import java.util.UUID;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)

// TODO:
//    @Test
//    public void internalSeverError() throws Exception

/**
 * The class for testing GreetingController.
 * @author Vidas
 */
public class GreetingControllerTest extends AbstractTest {
    @Override
    @BeforeAll
    public void setUp() {
        super.setUp();
    }

    AccountMessage account;
    String inputJson;
    String uri;
    MvcResult mvcResult;
    Activity activity;
    int status;

    /**
     * Registers a user.
     *
     * @throws Exception
     */
    @Test
    public void registerTest() throws Exception {

        String random = UUID.randomUUID().toString();
        uri = "/register";
        account = new AccountMessage(random, "password");
        inputJson = super.mapToJson(account);
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        DbAdaptor db = new DbAdaptor();
        db.deleteByUsername(random);
    }

    /**
     * Tests the default Get mapping.
     *
     * @throws Exception
     */
    @Test
    public void defaultMappingTest() throws Exception {

        uri = "/";

        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        status = mvcResult.getResponse().getStatus();
        assertEquals(301, status);
    }

    /**
     * Registers and tries to login with the registered credentials.
     *
     * @throws Exception
     */
    @Test
    public void loginTest() throws Exception {

        String random = UUID.randomUUID().toString();
        uri = "/register";
        account = new AccountMessage(random, "password");
        inputJson = super.mapToJson(account);
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        uri = "/login";
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();


        status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        DbAdaptor db = new DbAdaptor();
        db.deleteByUsername(random);
    }

    /**
     * Registers a user but tries to login with different credentials.
     *
     * @throws Exception
     */
    @Test
    public void loginTestWrongDetails() throws Exception {

        String random = UUID.randomUUID().toString();
        uri = "/register";
        account = new AccountMessage(random, "password");
        inputJson = super.mapToJson(account);
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();


        uri = "/login";
        account = new AccountMessage(random, "passworddsfsda");
        inputJson = super.mapToJson(account);
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();


        status = mvcResult.getResponse().getStatus();
        assertEquals(401, status);
        DbAdaptor db = new DbAdaptor();
        db.deleteByUsername(random);
    }

    /**
     * Tries to change the password of a user.
     *
     * @throws Exception
     */
    @Test
    public void changePassTest() throws Exception {

        String random = UUID.randomUUID().toString();
        uri = "/register";
        account = new AccountMessage(random, "password");
        inputJson = super.mapToJson(account);
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        uri = "/changepass";
        String[] testArray = {"0", random, "password1"};
        inputJson = super.mapToJson(testArray);
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        DbAdaptor db = new DbAdaptor();
        db.deleteByUsername(random);
    }

    /**
     * Attempts to register but the mocked dbadaptar rejects.
     */
    @Test
    public void registerFailure() throws Exception {
        GreetingController.setdbadaptor(Mockito.mock(DbAdaptor.class));
        when(GreetingController.getdbadaptor().addNewUser(
                new RegisterCredentials("user", "pass",
                        "question", "answer")))
                .thenReturn(false);
        uri = "/register";
        RegisterCredentials creds = new RegisterCredentials("user", "pass", "question", "answer");
        inputJson = super.mapToJson(creds);
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        status = mvcResult.getResponse().getStatus();
        assertEquals(500, status);
    }


    /**
     * Sends the server a login request without registering the user first.
     *
     * @throws Exception
     */
    /*@Test
    public void loginResponseTestUnauthorized() throws Exception {
        uri = "/login";
        account = new AccountMessage(UUID.randomUUID().toString(), "password");
        inputJson = super.mapToJson(account);
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        status = mvcResult.getResponse().getStatus();
        assertEquals(401, status);
    }*/

    /**
     * Registers the user and then sends the server a login request.
     *
     * @throws Exception
     */
    /*
    @Test
    public void loginResponseTestAuthorized() throws Exception {
        uri = "/register";
        account = new AccountMessage(UUID.randomUUID().toString(), "password");
        inputJson = super.mapToJson(account);
        mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson));

        String url = "/login";
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
    */

    /**
     * Tries registering the same user twice.
     *
     * @throws Exception
     */

//    @Test
//    public void registerTestTwice() throws Exception {
//
//
//        assertThrows(SQLException.class, () -> {uri = "/register";
//        account = new AccountMessage(UUID.randomUUID().toString(), "password");
//        inputJson = super.mapToJson(account);
//        mvc.perform(MockMvcRequestBuilders.post(uri)
//                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson));
//
//        mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
//                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//
//        status = mvcResult.getResponse().getStatus();});
//    }

    /**
     * Tests an activity with ID of 1.
     *
     * @throws Exception
     */
    /*@Test
    public void pointsTest() throws Exception {

        uri = "/points";
        activity = new Activity(1, 5);

        inputJson = super.mapToJson(activity);
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }*/

    /**
     * Tests an activity with an ID of 3 ("Not an activity").
     *
     * @throws Exception
     */
    /*@Test
    public void pointsTestNotAnActivity() throws Exception {

        uri = "/points";
        activity = new Activity(3, 5);

        inputJson = super.mapToJson(activity);
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }*/
}
