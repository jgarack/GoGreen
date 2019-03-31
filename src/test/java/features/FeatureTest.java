package features;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FeatureTest {
Feature fea = new Feature(10,1);
    @Test
    void test_getValue() {
        assertEquals(10,fea.getValue());
    }

    @Test
    void test_setValue() {
        fea.setValue(20);
        assertEquals(20, fea.getValue());
    }

    @Test
    void test_toString() {

        assertEquals("Feature{value=10}",(fea.toString()));
    }
}