package utility;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.assertTrue;

/**
 * Class for testing Emission class used for storing co2 emissions.
 * @author Omar Hussein
 */
public class EmissionTest {
    /**
     * Testing the constructor.
     */
    @Test
    public void constructorTest(){
        Emission emission = new Emission("52kg");
        assertNotNull(emission);
    }
    /**
     * Testing the emission Getter.
     */
    @Test
    public void getEmissionTest(){
        Emission emission = new Emission("52kg");
        assertEquals(emission.getEmission(),"52kg");
    }
    /**
     * Testing the emission Setter.
     */
    @Test
    public void setEmissionTest(){
        Emission emission = new Emission("52kg");
        emission.setEmission("28kg");
        assertTrue("The set method doesn't work properly",emission.getEmission().equals("28kg"));
    }
    /**
     * Testing the emission Setter while comparing it to the wrong value.
     */
    @Test
    public void setEmissionNotTrueTest(){
        Emission emission = new Emission("52kg");
        emission.setEmission("28kg");
        assertNotEquals(emission.getEmission(),"52kg");
    }
    /**
     * Testing the getter of emission's numerical value.
     */
    @Test
    public void getNumTest(){
        Emission emission = new Emission("52kg");
        assertEquals(emission.getNum(),52);
    }
    /**
     * Testing the getter of emission's numerical value while comparing it to the wrong value.
     */
    @Test
    public void getNumTestNotTrue(){
        Emission emission = new Emission("523kg");
        emission.setEmission("52kg");
        assertNotEquals(emission.getNum(),523);
    }
    /**
     * Testing the equals method with the same object.
     */
    @Test
    public void equalsSameObjTest(){
        Emission emission = new Emission("52kg");
        assertEquals(emission,emission);
    }
    /**
     * Testing the equals method with different but identical object.
     */
    @Test
    public void equalsTrue(){
        Emission emission = new Emission("52kg");
        Emission emission1 = new Emission("28kg");
        emission1.setEmission("52kg");
        assertEquals(emission,emission1);
    }
    /**
     * Testing the equals method with the same object.
     */
    @Test
    public void equalsFalse(){
        Emission emission = new Emission("52kg");
        Emission emission1 = new Emission("28kg");
        assertNotEquals(emission,emission1);
    }
    /**
     * Testing the equals method with two different class objects.
     */
    @Test
    public void equalsDifferentClasses(){
        Emission emission = new Emission("52kg");
        String emission1 = "28kg";
        assertNotEquals(emission,emission1);
    }



}
