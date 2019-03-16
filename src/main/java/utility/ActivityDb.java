package utility;

import java.sql.Date;

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
     * variable that stores the date.
     */
    private Date dateOfActivity;

    /**
     * variable that stores the username of the user who did the activity.
     */
    private User username;

    /**
     * Constructor.
     * @param actId number of activity.
     * @param scoreUser calculated score.
     * @param activityDate when the activity was done.
     * @param userName username of the user.
     */
    public ActivityDb(final int actId, final int scoreUser,
                      final Date activityDate, final User userName) {
        this.activityId = actId;
        this.score = scoreUser;
        this.dateOfActivity = activityDate;
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
