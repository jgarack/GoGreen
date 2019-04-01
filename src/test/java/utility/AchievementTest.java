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

    @Test
    void equalsSimilarObject() {
        Achievement testAchievement1 = new Achievement("name", true, "descr");
        assertTrue(testAchievement.equals(testAchievement1));
    }

    @Test
    void equalsSameObject() {
        assertTrue(testAchievement.equals(testAchievement));
    }

    @Test
    void equalsDifferentObject() {
        String testString = "test";
        assertFalse(testAchievement.equals(testString));
    }

    @Test
    void equalsNull() {
        assertFalse(testAchievement.equals(null));
    }


    @Test
    void equalsAchievedOther() {
        Achievement ach = new Achievement("name", false, "descr");
        assertFalse(testAchievement.equals(ach));
    }

    @Test
    void equalsNameAchievedOther() {
        Achievement ach = new Achievement("1name", false, "descr");
        assertFalse(testAchievement.equals(ach));
    }

    @Test
    void equalsdescAchievedOther() {
        Achievement ach = new Achievement("name", false, "descr1");
        assertFalse(testAchievement.equals(ach));
    }

    @Test
    void hashCodeTest() {
        assertEquals(testAchievement.hashCode(), 0);
    }
}