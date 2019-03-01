package utility;

import org.junit.Test;

import static org.junit.Assert.*;

public class ActivityTest {

    int num = 5;
    int weight = 80;
    Activity testActivity = new Activity(num, weight);

    @Test
    public void getId() {
        assertEquals(testActivity.getId(), 5);
    }

    @Test
    public void getValue() {
        assertEquals(testActivity.getValue(), 80);

    }

    @Test
    public void setValue() {
        testActivity.setValue(50);
        assertEquals(testActivity.getValue(), 50);
    }

    @Test
    public void setId() {
        testActivity.setId(50);
        assertEquals(testActivity.getId(), 50);
    }
}