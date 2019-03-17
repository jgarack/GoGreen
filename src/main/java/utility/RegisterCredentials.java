package utility;

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
     * @param username the username.
     * @param password the password.
     * @param secretQuestion set by user.
     * @param secretAnswer set by user.
     */
    public RegisterCredentials(final String username, final String password,
                               final String secretQuestion, final String secretAnswer) {
        this.username = username;
        this.password = password;
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

    /**
     * returns the username.
     * @return the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * returns the password.
     * @return the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets username.
     * @param username to be set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * sets password.
     * @param password to be set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Checks if fiven object is equal.
     * @param obj to be checked.
     * @return true if equal, false if not.
     */
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj instanceof RegisterCredentials) {
            RegisterCredentials regCre = (RegisterCredentials) obj;

            return regCre.getUsername().equals(this.getUsername())
                    && regCre.getAnswer().equals(this.getAnswer())
                    && regCre.getPassword().equals(this.getPassword())
                    && regCre.getQuestion().equals(this.getPassword());
        }

        return false;
    }
}