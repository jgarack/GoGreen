package utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActivityDbTest {
    ActivityDb testActivityDb = new ActivityDb(1, 1, 1, "test");

    @Test
    void getActivityId() {
        assertEquals(testActivityDb.getActivityId(), 1);
    }

    @Test
    void getScore() {
        assertEquals(testActivityDb.getScore(), 1);
    }

    @Test
    void getAmount() {
        assertEquals(testActivityDb.getAmount(), 1);
    }

    @Test
    void setActivityId() {
        testActivityDb.setActivityId(5);
        assertEquals(testActivityDb.getActivityId(), 5);
    }

    @Test
    void setScore() {
        testActivityDb.setScore(5);
        assertEquals(testActivityDb.getScore(), 5);
    }

    @Test
    void setAmount() {
        testActivityDb.setAmount(5);
        assertEquals(testActivityDb.getAmount(), 5);
    }

    @Test
    void increaseAmount() {
        testActivityDb.setAmount(5);
        testActivityDb.increaseAmount(5);
        assertEquals(testActivityDb.getAmount(), 10);
    }

    @Test
    void setUsername() {
        testActivityDb.setUsername("test1");
        assertTrue(testActivityDb.getUsernameAct().equals("test1"));
    }

    @Test
    void getUsernameAct() {
        String name = "test";
        assertTrue(testActivityDb.getUsernameAct().equals(name));
    }
}