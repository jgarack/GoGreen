package utility;

/**
 * Class for User.
 */
public class User {
    /**
     * Id of the user.
     */
    private int userId;
    /**
     * Username of the user.
     */
    private String username;
    /**
     * Encrypted password of the user.
     */
    private String encPassword;
    /**
     * The gender of the user.
     */
    private String gender;
    /**
     * Id of the Secret question.
     */
    private int questionId;
    /**
     * Answer to the secret question.
     */
    private String answer;
    /**
     * Total score of the user.
     */
    private int totalScore;
    /**
     * Date of birth of the user.
     */
    private String dateOfBirth;

    /**
     * Default constructor of the User class.
     * @param userid id of the user.
     * @param userName username of the user.
     * @param encryptedPassword encrypted password of the user.
     * @param sex Gender of the user.
     * @param questionid Id of secret question.
     * @param answerQ Answer to the secret question.
     * @param userTotalScore Total score of the user.
     * @param birthDate Date of birth of the user.
     */
    public User(final int userid, final String userName,
                final String encryptedPassword, final String sex,
                final int questionid, final String answerQ,
                final int userTotalScore, final String birthDate) {
        this.userId = userid;
        this.username = userName;
        this.encPassword = encryptedPassword;
        this.gender = sex;
        this.questionId = questionid;
        this.answer = answerQ;
        this.totalScore = userTotalScore;
        this.dateOfBirth = birthDate;
    }

    /**
     * Get id of the user.
     * @return Id of the user.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets new id to the user.
     * @param userid The id to be set.
     */
    public void setUserId(final int userid) {
        this.userId = userid;
    }

    /**
     * Retrieves username of the user.
     * @return the username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets a new username to the user.
     * @param userName the username to be set.
     */
    public void setUsername(final String userName) {
        this.username = userName;
    }

    /**
     * Retrieves encrypted password of the user.
     * @return the password of the user.
     */
    public String getEncPassword() {
        return encPassword;
    }

    /**
     * Sets a new encrypted password to the user.
     * @param encryptedPassword the password to be set.
     */
    public void setEncPassword(final String encryptedPassword) {
        this.encPassword = encryptedPassword;
    }

    /**
     * Retrieves gender of the user.
     * @return the gender of the user.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets a new gender to the user.
     * @param genderUser the gender to be set.
     */
    public void setGender(final String genderUser) {
        this.gender = genderUser;
    }

    /**
     * Retrieves id to the secret question of the user.
     * @return the id of the user.
     */
    public int getQuestionId() {
        return questionId;
    }

    /**
     * Sets a new id to the secret question to the user.
     * @param questionid the id to be set.
     */
    public void setQuestionId(final int questionid) {
        this.questionId = questionid;
    }

    /**
     * Retrieves answer of the user.
     * @return the answer of the user.
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Sets a new answer to the user.
     * @param answerQ the answer to be set.
     */
    public void setAnswer(final String answerQ) {
        this.answer = answerQ;
    }

    /**
     * Retrieves total score of the user.
     * @return the total score of the user.
     */
    public int getTotalScore() {
        return totalScore;
    }

    /**
     * Sets a new total score to the user.
     * @param userTotalScore the total score to be set.
     */
    public void setTotalScore(final int userTotalScore) {
        this.totalScore = userTotalScore;
    }

    /**
     * Retrieves date of birth of the user.
     * @return the date of birth of the user.
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets a new date of birth to the user.
     * @param birthDate the date of birth to be set.
     */
    public void setDateOfBirth(final String birthDate) {
        this.dateOfBirth = birthDate;
    }

    /**
     * Generates a string representation of the user.
     * @return A human-friendly string.
     */
    @Override
    public String toString() {
        return "User{"
                + "userId=" + userId
                + ", username='" + username + '\''
                + ", encPassword='" + encPassword + '\''
                + ", gender='" + gender + '\''
                + ", questionId=" + questionId
                + ", answer='" + answer + '\''
                + ", totalScore=" + totalScore
                + ", dateOfBirth='" + dateOfBirth + '\'' + '}';
    }
}
