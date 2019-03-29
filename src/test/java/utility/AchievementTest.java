package utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AchievementTest {

    Achievement testAchievement = new Achievement("name", true, "descr");

    @Test
    void isAchieved() {
        assertTrue(testAchievement.isAchieved());
    }

    @Test
    void setAchieved() {
        testAchievement.setAchieved(false);
        assertFalse(testAchievement.isAchieved());
    }

    @Test
    void getName() {
        String name = "name";
        assertTrue(name.equals(testAchievement.getName()));
    }

    @Test
    void setName() {
        String name = "nameee";
        testAchievement.setName(name);
        assertTrue(name.equals(testAchievement.getName()));
    }

    @Test
    void getDescription() {
        String descr = "descr";
        assertTrue(descr.equals(testAchievement.getDescription()));
    }

    @Test
    void setDescription() {
        String descr = "description";
        testAchievement.setDescription(descr);
        assertTrue(descr.equals(testAchievement.getDescription()));

    }
}