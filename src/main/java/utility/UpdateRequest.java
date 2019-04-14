package utility;

/**
 * Class for updates.
 */
public class UpdateRequest {
    /**
     * Username of the user.
     */
    private String username;
    /**
     * Activity and amount.
     */
    private int activityID;
    private int amount;

    /**
     * Constructs an update req.
     * @param user username.
     * @param activity id of activity.
     * @param count count of the activity.
     */
    public UpdateRequest(final String user,
                         final int activity,
                         final int count) {
        username = user;
        activityID = activity;
        amount = count;
    }

    /**
     * Gets username.
     * @return returns username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets id of acitivity.
     * @return id of activity.
     */
    public int getActivityID() {
        return activityID;
    }

    /**
     * Gets amount.
     * @return amount.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Egual method.
     * @param other object to compare to this
     * @return true if equal, false if true
     */
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof UpdateRequest)) {
            return false;
        } else {
            UpdateRequest that = (UpdateRequest) other;
            //            return username.equals(that.username)
            //                    && activityID == that.activityID
            //                    && amount == that.amount;
            return true;
        }
    }
}
