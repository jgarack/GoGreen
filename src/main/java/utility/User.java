package utility;

public class User {
    private int user_id;
    private String username;
    private String encPassword;
    private String gender;
    private int question_id;
    private String answer;
    private int totalScore;
    private String dateOfBirth;

    public User(int user_id, String username, String encPassword, String gender, int question_id, String answer, int totalScore, String dateOfBirth) {
        this.user_id = user_id;
        this.username = username;
        this.encPassword = encPassword;
        this.gender = gender;
        this.question_id = question_id;
        this.answer = answer;
        this.totalScore = totalScore;
        this.dateOfBirth = dateOfBirth;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEncPassword() {
        return encPassword;
    }

    public void setEncPassword(String encPassword) {
        this.encPassword = encPassword;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", encPassword='" + encPassword + '\'' +
                ", gender='" + gender + '\'' +
                ", question_id=" + question_id +
                ", answer='" + answer + '\'' +
                ", totalScore=" + totalScore +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                '}';
    }
}
