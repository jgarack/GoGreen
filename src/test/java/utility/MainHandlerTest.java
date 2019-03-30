package utility;

import gui.AlertBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.StringReader;
import java.nio.Buffer;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class MainHandlerTest {
    private static final int AMOUNT = 1;
    private static final BufferedReader BR_FALSE = new BufferedReader(
            new StringReader("false"));
    private static final BufferedReader BR_ONE = new BufferedReader(
            new StringReader("1"));


    MainHandler testObject;

    @BeforeAll
    public static void setUp() throws Exception {
        MainHandler.username = "user";
    }

    @BeforeEach
    public void setMocks() {
        HttpRequestHandler mockedHandler = Mockito.mock(HttpRequestHandler.class);
        AlertBuilder mockedBuilder = Mockito.mock(AlertBuilder.class);
        testObject = new MainHandler(null);
        testObject.setHttpHandler(mockedHandler);
        testObject.setAlertBuilder(mockedBuilder);
    }

    @Test
    public void tryParseIntTest() throws Exception {
        assertTrue(testObject.tryParseInt("500"));
    }

    @Test
    public void tryParseIntTestFail() throws Exception {
        assertFalse(testObject.tryParseInt("hello"));
    }

    @Test
    public void updateVeg_succeed() throws Exception {
        when(testObject.getHttpHandler().reqPost("/points",
                new UpdateRequest(MainHandler.username, 1, AMOUNT)))
                .thenReturn(BR_ONE);
        assertEquals(1, testObject.updateVegMeal(AMOUNT));
    }

    @Test
    public void updateBike_succeed() throws Exception {
        when(testObject.getHttpHandler().reqPost("/points",
                new UpdateRequest(MainHandler.username, 2, AMOUNT)))
                .thenReturn(BR_ONE);
        assertEquals(1, testObject.updateBike(AMOUNT));
    }

    @Test
    public void updateLocal_succeed() throws Exception {
        when(testObject.getHttpHandler().reqPost("/points",
                new UpdateRequest(MainHandler.username, 3, AMOUNT)))
                .thenReturn(BR_ONE);
        assertEquals(1, testObject.updateLocal(AMOUNT));
    }

    @Test
    public void updateBus_succeed() throws Exception {
        when(testObject.getHttpHandler().reqPost("/points",
                new UpdateRequest(MainHandler.username, 4, AMOUNT)))
                .thenReturn(BR_ONE);
        assertEquals(1, testObject.updateBus(AMOUNT));
    }

    @Test
    public void updateSolar_failure() throws Exception {
        when(testObject.getHttpHandler().reqPost("/points",
                new UpdateRequest(MainHandler.username, 5, AMOUNT)))
                .thenReturn(BR_FALSE);
        assertEquals(0, testObject.updateSolar(AMOUNT));
    }

    @Test
    public void updateSolar_succeed() throws Exception {
        when(testObject.getHttpHandler().reqPost("/points",
                new UpdateRequest(MainHandler.username, 5, AMOUNT)))
                .thenReturn(BR_ONE);
        assertEquals(1, testObject.updateSolar(AMOUNT));
    }

}
