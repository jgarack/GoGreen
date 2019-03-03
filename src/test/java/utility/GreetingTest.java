package utility;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetingTest {
    private static final long FIVETHOUSAND = 5000;
    private static final String CONTENT = "content";
    private static final Greeting TESTGREETING = new Greeting(FIVETHOUSAND, CONTENT);

    @Test
    public void getId() {
        assertEquals(FIVETHOUSAND, TESTGREETING.getId() );
    }

    @Test
    public void getContent() {
        assertEquals(CONTENT, TESTGREETING.getContent());
    }
}