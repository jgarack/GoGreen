package utility;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Class used to test the User class.
 * @author Jonathan
 */
public class UserTest {

    String username = "username";
    String gender = "male";
    int totalScore = 1000;
    String dateOfBirth = "dd.mm.yy";

    User user = new User(username, gender, totalScore, dateOfBirth);


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
     * Gender getter test.
     */
    @Test
    void getGenderTest() {
        assertEquals(gender, user.getGender());
    }

    /**
     * Gender setter test.
     */
    @Test
    void setGenderTest() {
        user.setGender("female");
        assertEquals("female", user.getGender());
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
     * DateOfBirth getter test.
     */
    @Test
    void getDateOfBirthTest() {
        assertEquals(dateOfBirth, user.getDateOfBirth());
    }

    /**
     * DateOfBirth setter test.
     */
    @Test
    void setDateOfBirthTest() {
        user.setDateOfBirth("dd.mm.rr");
        assertEquals("dd.mm.rr", user.getDateOfBirth());
    }

    /**
     * ToString method test.
     */
    @Test
    void toStringEmptyTest() {
        String expected = "User{"
                + ", username='" + username + '\''
                + ", gender='" + gender + '\''
                + ", totalScore=" + totalScore
                + ", dateOfBirth='" + dateOfBirth + '\'' + '}';
        assertEquals(expected, user.toString());
    }
}
