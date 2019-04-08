package utility;

import java.util.Objects;

/**
 * Credentials for the secret question.
 */
public class RegisterCredentials {

    /**
     * variable to store username.
     */
    private String username;

    /**
     * variable to store username.
     */
    private String password;
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
     *
     * @param userName       the username.
     * @param pass           the password.
     * @param secretQuestion set by user.
     * @param secretAnswer   set by user.
     */
    public RegisterCredentials(final String userName,
                               final String pass,
                               final String secretQuestion,
                               final String secretAnswer) {
        this.username = userName;
        this.password = pass;
        this.question = secretQuestion;
        this.answer = secretAnswer;
    }

    /**
     * returns the question set by user.
     *
     * @return question.
     */
    public String getQuestion() {
        return question;
    }

    /**
     * returns the answer set by user.
     *
     * @return answer.
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * returns the username.
     *
     * @return the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * returns the password.
     *
     * @return the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets username.
     *
     * @param userName to be set.
     */
    public void setUsername(final String userName) {
        this.username = userName;
    }

    /**
     * sets password.
     *
     * @param pass to be set.
     */
    public void setPassword(final String pass) {
        this.password = pass;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        RegisterCredentials that = (RegisterCredentials) o;

        return Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(question, that.question) &&
                Objects.equals(answer, that.answer);
    }

}