package utility;

/**
 * Credentials for the secret question.
 */
public class QuestionCredentials {

    /**
     * Variable that holds the question.
     */
    private String question;

    /**
     * Variable that holds the answer.
     */
    private String answer;

    /**
     * Constructor.
     * @param secretQuestion set by user.
     * @param secretAnswer set by user.
     */
    public QuestionCredentials(final String secretQuestion,
                               final String secretAnswer) {
        this.question = secretQuestion;
        this.answer = secretAnswer;
    }

    /**
     * returns the question set by user.
     * @return question.
     */
    public String getQuestion() {
        return question;
    }

    /**
     * returns the answer set by user.
     * @return answer.
     */
    public String getAnswer() {
        return answer;
    }
}
