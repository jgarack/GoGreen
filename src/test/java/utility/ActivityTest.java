package utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The class for testing Activity class.
 * @author Vidas
 */
public class ActivityTest {

    int num = 5;
    int weight = 80;
    Activity testActivity = new Activity(num, weight);

    /**
     * Id getter test.
     */
    @Test
    public void getId() {
        assertEquals(testActivity.getId(), 5);
    }

    /**
     * Value getter test.
     */
    @Test
    public void getValue() {
        assertEquals(testActivity.getValue(), 80);

    }

    /**
     * Value setter test.
     */
    @Test
    public void setValue() {
        testActivity.setValue(50);
        assertEquals(testActivity.getValue(), 50);
    }

    /**
     * Id setter test.
     */
    @Test
    public void setId() {
        testActivity.setId(50);
        assertEquals(testActivity.getId(), 50);
    }
}