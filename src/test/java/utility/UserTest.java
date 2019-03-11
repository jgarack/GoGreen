package utility;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Class used to test the User class.
 * @author Jonathan
 */
public class UserTest {

    int userId = 1;
    String username = "username";
    String encPassword = "password";
    String gender = "male";
    int questionId = 1;
    String answer = "answer";
    int totalScore = 1000;
    String dateOfBirth = "dd.mm.yy";

    User user = new User(userId, username, encPassword, gender, questionId, answer, totalScore, dateOfBirth);

    /**
     * UserID getter test.
     */
    @Test
    void getUserIdTest() {
        assertEquals(userId, user.getUserId());
    }

    /**
     * UserID setter test.
     */
    @Test
    void setUserIdTest() {
        user.setUserId(2);
        assertEquals(2,user.getUserId());
    }

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
     * Encoded password getter test.
     */
    @Test
    void getEncPasswordTest() {
        assertEquals(encPassword, user.getEncPassword());
    }

    /**
     * Encoded password setter test.
     */
    @Test
    void setEncPasswordTest() {
        user.setEncPassword("password2");
        assertEquals("password2", user.getEncPassword());
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
     * QuestionID getter test.
     */
    @Test
    void getQuestionIdTest() {
        assertEquals(questionId, user.getQuestionId());
    }

    /**
     * QuestionID setter test.
     */
    @Test
    void setQuestionIdTest() {
        user.setQuestionId(2);
        assertEquals(2, user.getQuestionId());

    }

    /**
     * Answer getter test.
     */
    @Test
    void getAnswerTest() {
        assertEquals(answer, user.getAnswer());
    }

    /**
     * Answer setter test.
     */
    @Test
    void setAnswerTest() {
        user.setAnswer("answer2");
        assertEquals("answer2", user.getAnswer());
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
                + "userId=" + userId
                + ", username='" + username + '\''
                + ", encPassword='" + encPassword + '\''
                + ", gender='" + gender + '\''
                + ", questionId=" + questionId
                + ", answer='" + answer + '\''
                + ", totalScore=" + totalScore
                + ", dateOfBirth='" + dateOfBirth + '\'' + '}';
        assertEquals(expected, user.toString());
    }
}
