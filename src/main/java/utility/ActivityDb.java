package utility;


/**
 * Class for the activities.
 */
public class ActivityDb {

    /**
     * variable that stores activityId.
     */
    private int activityId;

    /**
     * variable that stores score.
     */
    private int score;

    /**
     * variable that stores the amount of times an activity was completed.
     */
    private int amount;

    /**
     * variable that stores the username of the user who did the activity.
     */
    private String username;

    /**
     * Constructor.
     * @param actId number of activity.
     * @param scoreUser calculated score.
     * @param count when the activity was done.
     * @param userName username of the user.
     */
    public ActivityDb(final int actId, final int scoreUser,
                      final int count, final String userName) {
        this.activityId = actId;
        this.score = scoreUser;
        this.amount = count;
        this.username = userName;
    }

    /**
     * returns the activityId.
     * @return the activityId.
     */
    public int getActivityId() {
        return activityId;
    }

    /**
     * returns the score.
     * @return the score.
     */
    public int getScore() {
        return score;
    }

    /**
     * returns the date of the activity.
     * @return the date of the activity.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets the id of the activity.
     * @param activity the id to be set.
     */
    public void setActivityId(final int activity) {
        this.activityId = activity;
    }

    /**
     * Sets the score of the user.
     * @param userScore the score to be set.
     */
    public void setScore(final int userScore) {
        this.score = userScore;
    }

    /**
     *  Sets amount.
     * @param count the amount to be set.
     */
    public void setAmount(final int count) {
        this.amount = count;
    }

    /**
     * Increases amount with count.
     * @param count Increases the amount.
     */
    public void increaseAmount(final int count) {
        this.amount += count;
    }

    /**
     * Sets a new username.
     * @param name The username to be set.
     */
    public void setUsername(final String name) {
        this.username = name;
    }

    /**
     * returns the username of the user.
     * @return the username of the user.
     */
    public String getUsernameAct() {
        return username;
    }
}
