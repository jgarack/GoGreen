package server;

import org.junit.Test;
import utility.Greeting;

import static org.junit.Assert.*;

public class GreetingTest {

    long id = 5000;
    String content = "content";

    Greeting testGreeting = new Greeting(id, content);

    @Test
    public void getId() {
        assertEquals(5000, testGreeting.getId() );
    }

    @Test
    public void getContent() {
        assertEquals("content", testGreeting.getContent());
    }
}