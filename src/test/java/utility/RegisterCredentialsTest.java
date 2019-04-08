package utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterCredentialsTest {

    RegisterCredentials RC1 = new RegisterCredentials("username", "password", "question", "answer");



    @Test
    void constructorTest() {

        RegisterCredentials RC3 = new RegisterCredentials("username", "password", "question", "answer");
        assertEquals("password", RC3.getPassword());
        assertEquals("username", RC3.getUsername());
        assertEquals("question", RC3.getQuestion());
        assertEquals("answer", RC3.getAnswer());


    }

    @Test
    void getQuestion() {
        assertEquals(RC1.getQuestion(), "question");
    }

    @Test
    void getAnswer() {
        assertEquals(RC1.getAnswer(), "answer");
    }

    @Test
    void getUsername() {
        assertEquals(RC1.getUsername(), "username");
    }

    @Test
    void getPassword() {
        assertEquals(RC1.getPassword(), "password");
    }

    @Test
    void setUsername() {
        RC1.setUsername("user");
        assertEquals("user", RC1.getUsername());
    }

    @Test
    void setPassword() {
        RC1.setPassword("pass");
        assertEquals("pass", RC1.getPassword());
    }

    @Test
    void equals_True() {
        RegisterCredentials RC2 = new RegisterCredentials(RC1.getUsername(),
                RC1.getPassword(), RC1.getQuestion(), RC1.getAnswer());
        assertTrue(RC1.equals(RC2));
    }
    @Test
    void equals_Self() {
        assertTrue(RC1.equals(RC1));
    }
    @Test
    void equals_False_Type() {
        assertFalse(RC1.equals(new Object()));
    }
    @Test
    void equals_False_User() {
        RegisterCredentials RC2 = new RegisterCredentials("random",
                RC1.getPassword(), RC1.getQuestion(), RC1.getAnswer());
        assertFalse(RC1.equals(RC2));
    }
    @Test
    void equals_False_Pass() {
        RegisterCredentials RC2 = new RegisterCredentials(RC1.getUsername(),
                "random", RC1.getQuestion(), RC1.getAnswer());
        assertFalse(RC1.equals(RC2));
    }
    @Test
    void equals_False_Question() {
        RegisterCredentials RC2 = new RegisterCredentials(RC1.getUsername(),
                RC1.getPassword(), "random", RC1.getAnswer());
        assertFalse(RC1.equals(RC2));
    }
    @Test
    void equals_False_Answer() {
        RegisterCredentials RC2 = new RegisterCredentials(RC1.getUsername(),
                RC1.getPassword(), RC1.getQuestion(), "random");
    }
}