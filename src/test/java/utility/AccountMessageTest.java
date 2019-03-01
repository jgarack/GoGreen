package utility;

import org.junit.Test;

import static org.junit.Assert.*;

public class AccountMessageTest {
    String username = "username";
    String password = "password";
    AccountMessage testAccountMessage = new AccountMessage(username, password);

    @Test
    public void getUsername() {
        assertEquals(username,testAccountMessage.getUsername());
    }

    @Test
    public void getPassword() {
        assertEquals(password,testAccountMessage.getPassword());

    }

    @Test
    public void setPassword() {
        testAccountMessage.setPassword("newPassword");
        assertEquals("newPassword",testAccountMessage.getPassword());

    }

    @Test
    public void setUsername() {
        testAccountMessage.setUsername("newUsername");
        assertEquals("newUsername",testAccountMessage.getUsername());
    }

    @Test
    public void equalsTrue() {
        AccountMessage testAccountMessageSame = new AccountMessage(username, password );
        assertEquals(testAccountMessage,testAccountMessageSame);
    }

    @Test
    public void equalsSelf(){
        assertEquals(testAccountMessage, testAccountMessage);
    }

    @Test
    public void equalsDifferentValue() {
        AccountMessage testAccountMessageDifferent = new AccountMessage("usergdname", "passdfsword");
        assertNotEquals(testAccountMessage, testAccountMessageDifferent);
    }

    @Test
    public void equalsDifferentType() {
        String test = "test";
        assertNotEquals(testAccountMessage, test);
    }
}