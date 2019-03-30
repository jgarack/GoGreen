package utility;

import gui.AlertBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.StringReader;
import java.nio.Buffer;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public void updateSolar_succeed() throws Exception {
        when(testObject.getHttpHandler().reqPost("/points",
                new UpdateRequest(MainHandler.username, 5, AMOUNT)))
                .thenReturn(BR_ONE);
        assertEquals(1, testObject.updateSolar(AMOUNT));
    }
    @Test
    public void updateSolar_failure() throws Exception {
        when(testObject.getHttpHandler().reqPost("/points",
                new UpdateRequest(MainHandler.username, 5, AMOUNT)))
                .thenReturn(BR_FALSE);
        assertEquals(0, testObject.updateSolar(AMOUNT));
    }
}
