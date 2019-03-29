package utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateRequestTest {

    UpdateRequest test = new UpdateRequest("user",1,1 );

    @Test
    void getUsername() {
        assertTrue(test.getUsername().equals("user"));
    }

    @Test
    void getActivityID() {
        assertEquals(test.getActivityID(), 1);
    }

    @Test
    void getAmount() {
        assertEquals(test.getAmount(), 1);

    }
}