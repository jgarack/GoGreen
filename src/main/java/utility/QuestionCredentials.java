package utility;

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
     * @param question set by user.
     * @param answer set by user.
     */
    public QuestionCredentials(String question, String answer) {
        this.question = question;
        this.answer = answer;
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
