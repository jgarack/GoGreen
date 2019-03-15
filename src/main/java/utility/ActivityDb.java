package utility;

import java.sql.Date;

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
     * variable that stores the date.
     */
    private Date dateOfActivity;

    /**
     * variable that stores the username of the user who did the activity.
     */
    private User username;

    /**
     * Constructor.
     * @param activityId number of activity.
     * @param score calculated score.
     * @param dateOfActivity when the activity was done.
     * @param username username of the user.
     */
    public ActivityDb(int activityId, int score, Date dateOfActivity, User username) {
        this.activityId = activityId;
        this.score = score;
        this.dateOfActivity = dateOfActivity;
        this.username = username;
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
    public Date getDateOfActivity() {
        return dateOfActivity;
    }

    /**
     * returns the username of the user.
     * @return the username of the user.
     */
    public User getUsernameAct() {
        return username;
    }
}
