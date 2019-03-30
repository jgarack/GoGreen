package utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateRequestTest {

    UpdateRequest test = new UpdateRequest("user", 1, 1);

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

    @Test
    void equalsSimilarObject() {
        UpdateRequest test1 = new UpdateRequest("user", 1, 1);
        assertTrue(test1.equals(test));
    }

    @Test
    void equalsSameObject() {
        assertTrue(test.equals(test));
    }

    @Test
    void equalsDifferentObject() {
        String testString = "test";
        assertFalse(test.equals(testString));
    }
}