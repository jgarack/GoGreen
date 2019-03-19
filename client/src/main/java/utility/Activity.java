package utility;

/**Activity object to be communicated to the server.
 *with id specific to each activity
 */
public class Activity {
    /**
     * Id to identify type of activity ex: 1 = Vegetarian meal.
     */
    private int id;
    /**
     * amount of activity performed.
     */
    private int value;

    /**Activity constructor.
     * @param num activity identifier
     * @param weight amount of activity performed
     * @author ohussein
     */
    public Activity(final int num, final int weight) {
        this.id = num;
        this.value = weight;
    }

    /**Getter for Id.
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**Getter for Value.
     *
     * @return value
     */
    public int getValue() {
        return value;
    }

    /**Setter for Value.
     *
     * @param weight amount of activity performed
     */
    public void setValue(final int weight) {
        this.value = weight;
    }
    /**Setter for id.
     *
     * @param num id of activity
     */
    public void setId(final int num) {
        this.id = num;
    }
}
