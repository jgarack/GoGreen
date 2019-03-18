package utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginCredentialsTest {

    LoginCredentials LC1 = new LoginCredentials("username", "password");
    LoginCredentials LC2 = new LoginCredentials("username", "password");


    @Test
    void constructorTest() {

        assertEquals(LC1,LC2);

    }

    @Test
    void getUsername() {

        assertEquals(LC1.getUsername(), "username");

    }

    @Test
    void getPassword() {

        assertEquals(LC1.getPassword(), "password");

    }

    @Test
    void setUsername() {

        LC1.setUsername("otherUsername");
        assertEquals(LC1.getUsername(), "otherUsername");

    }

    @Test
    void setPassword() {

        LC1.setPassword("otherPassword");
        assertEquals(LC1.getPassword(), "otherPassword");

    }

    @Test
    void notEquals() {
        LC2.setPassword("pass");
        assertNotEquals(LC1, LC2);
    }

    @Test
    void Equals() {
        assertEquals(LC1, LC2);
    }

    @Test
    void nullEquals() {
        assertNotEquals(null, LC2);
    }

    @Test
    void objectEquals() {
        assertNotEquals(new Object(), LC2);
    }
    
}