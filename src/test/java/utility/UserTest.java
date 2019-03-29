package utility;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Class used to test the User class.
 * @author Jonathan
 */
public class UserTest {

    String username = "username";
    int totalScore = 1000;

    User user = new User(username, totalScore);


    /**
     * Username getter test.
     */
    @Test
    void getUsernameTest() {
        assertEquals(username,user.getUsername());
    }

    /**
     * Username setter test.
     */
    @Test
    void setUsernameTest() {
        user.setUsername("username2");
        assertEquals("username2", user.getUsername());
    }


    /**
     * TotalScore getter test.
     */
    @Test
    void getTotalScoreTest() {
        assertEquals(totalScore,user.getTotalScore());
    }

    /**
     * TotalScore setter test.
     */
    @Test
    void setTotalScoreTest() {
        user.setTotalScore(1100);
        assertEquals(1100, user.getTotalScore());
    }

    /**
     * ToString method test.
     */
    @Test
    void toStringEmptyTest() {
        String expected = "User{"
                + ", username='" + username + '\''
                + ", totalScore=" + totalScore + '\'' + '}';
        assertEquals(expected, user.toString());
    }

    @Test
    void getFriendsTest() {
        user.setUsername("username2");
        assertEquals("username2", user.getUsername());
    }
}
