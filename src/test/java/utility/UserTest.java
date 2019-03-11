package utility;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

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
     * getUserId test.
     */
    @Test
    void getUserIdTest() {
        assertEquals(userId, user.getUserId());
    }

    /**
     * setUserId test.
     */
    @Test
    void setUserIdTest() {
        user.setUserId(2);
        assertEquals(2,user.getUserId());
    }

    /**
     * getUsername test.
     */
    @Test
    void getUsernameTest() {
        assertEquals(username,user.getUsername());
    }

    /**
     * setUsername test.
     */
    @Test
    void setUsernameTest() {
        user.setUsername("username2");
        assertEquals("username2", user.getUsername());
    }

    /**
     * getEncPassword test.
     */
    @Test
    void getEncPasswordTest() {
        assertEquals(encPassword, user.getEncPassword());
    }

    /**
     * setEncPassword test.
     */
    @Test
    void setEncPasswordTest() {
        user.setEncPassword("password2");
        assertEquals("password2", user.getEncPassword());
    }

    /**
     * getGender test.
     */
    @Test
    void getGenderTest() {
        assertEquals(gender, user.getGender());
    }

    /**
     * setGender test.
     */
    @Test
    void setGenderTest() {
        user.setGender("female");
        assertEquals("female", user.getGender());
    }

    /**
     * getQuestionId test.
     */
    @Test
    void getQuestionIdTest() {
        assertEquals(questionId, user.getQuestionId());
    }

    /**
     * setQuestionId test.
     */
    @Test
    void setQuestionIdTest() {
        user.setQuestionId(2);
        assertEquals(2, user.getQuestionId());

    }

    /**
     * getAnswer test.
     */
    @Test
    void getAnswerTest() {
        assertEquals(answer, user.getAnswer());
    }

    /**
     * setAnswer test.
     */
    @Test
    void setAnswerTest() {
        user.setAnswer("answer2");
        assertEquals("answer2", user.getAnswer());
    }

    /**
     * getTotalScore test.
     */
    @Test
    void getTotalScoreTest() {
        assertEquals(totalScore,user.getTotalScore());
    }

    /**
     * setTotalScore test.
     */
    @Test
    void setTotalScoreTest() {
        user.setTotalScore(1100);
        assertEquals(1100, user.getTotalScore());
    }

    /**
     * getDateOfBirth test.
     */
    @Test
    void getDateOfBirthTest() {
        assertEquals(dateOfBirth, user.getDateOfBirth());
    }

    /**
     * setDateOfBirth test.
     */
    @Test
    void setDateOfBirthTest() {
        user.setDateOfBirth("dd.mm.rr");
        assertEquals("dd.mm.rr", user.getDateOfBirth());
    }

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
