package utility;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The class for testing AccountMessage class.
 *
 * @author Vidas
 */
public class AccountMessageTest {
    String username = "username";
    String password = "password";
    AccountMessage testAccountMessage = new AccountMessage(username, password);

    /**
     * Username getter test.
     */
    @Test
    public void getUsername() {
        assertEquals(username, testAccountMessage.getUsername());
    }

    /**
     * Password getter test.
     */
    @Test
    public void getPassword() {
        assertEquals(password, testAccountMessage.getPassword());

    }

    /**
     * Password setter test.
     */
    @Test
    public void setPassword() {
        testAccountMessage.setPassword("newPassword");
        assertEquals("newPassword", testAccountMessage.getPassword());

    }

    /**
     * Username setter test.
     */
    @Test
    public void setUsername() {
        testAccountMessage.setUsername("newUsername");
        assertEquals("newUsername", testAccountMessage.getUsername());
    }

    /**
     * Equals method test when the object is equal to the other object.
     */
    @Test
    public void equalsTrue() {
        AccountMessage testAccountMessageSame = new AccountMessage(username, password);
        assertEquals(testAccountMessage, testAccountMessageSame);
    }

    /**
     * Equals method test when the object is compared to itself.
     */
    @Test
    public void equalsSelf() {
        assertEquals(testAccountMessage, testAccountMessage);
    }

    /**
     * Equals method test when the usernames of objects are different.
     */
    @Test
    public void equalsDifferentValue() {
        AccountMessage testAccountMessageDifferent = new AccountMessage("usergdname", "passdfsword");
        assertNotEquals(testAccountMessage, testAccountMessageDifferent);
    }

    /**
     * Equals method test when the types of two objects are different.
     */
    @Test
    public void equalsDifferentType() {
        String test = "test";
        assertNotEquals(testAccountMessage, test);
    }

    /**
     * Hashcode test.
     */
    @Test
    public void hashCodeTest() {
        int hashcode = testAccountMessage.hashCode();
        assertTrue(testAccountMessage.hashCode() != 0);
    }
}