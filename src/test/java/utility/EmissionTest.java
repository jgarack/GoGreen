package utility;

import exceptions.DataConflictException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertTrue;

public class EmissionTest {

    @Test
    public void constructorTest(){
        Emission emission = new Emission("52kg");
        assertNotNull(emission);
    }

    @Test
    public void getEmissionTest(){
        Emission emission = new Emission("52kg");
        assertEquals(emission.getEmission(),"52kg");
    }

    @Test
    public void setEmissionTest(){
        Emission emission = new Emission("52kg");
        emission.setEmission("28kg");
        assertTrue("The set method doesn't work properly",emission.getEmission().equals("28kg"));
    }

    @Test
    public void setEmissionNotTrueTest(){
        Emission emission = new Emission("52kg");
        emission.setEmission("28kg");
        assertNotEquals(emission.getEmission(),"52kg");
    }

    @Test
    public void getNumTest(){
        Emission emission = new Emission("52kg");
        assertEquals(emission.getNum(),52);
    }

    @Test
    public void getNumTestNotTrue(){
        Emission emission = new Emission("523kg");
        emission.setEmission("52kg");
        assertNotEquals(emission.getNum(),523);
    }

    @Test
    public void equalsSameObjTest(){
        Emission emission = new Emission("52kg");
        assertEquals(emission,emission);
    }

    @Test
    public void equalsTrue(){
        Emission emission = new Emission("52kg");
        Emission emission1 = new Emission("28kg");
        emission1.setEmission("52kg");
        assertEquals(emission,emission1);
    }
    @Test
    public void equalsFalse(){
        Emission emission = new Emission("52kg");
        Emission emission1 = new Emission("28kg");
        assertNotEquals(emission,emission1);
    }
    @Test
    public void equalsDifferentClasses(){
        Emission emission = new Emission("52kg");
        String emission1 = "28kg";
        assertNotEquals(emission,emission1);
    }



}
