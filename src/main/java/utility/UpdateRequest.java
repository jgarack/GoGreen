package utility;

public class UpdateRequest {
    private String username;
    private int activityID, amount;
    public UpdateRequest(final String user, final int activity, final int count) {
        username = user;
        activityID = activity;
        amount = count;
    }

    public String getUsername() {
        return username;
    }
    public int getActivityID() {
        return activityID;
    }
    public int getAmount() {
        return amount;
    }
}
