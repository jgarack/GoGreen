package utility;

/**Activity object to be communicated to the server
 *with id specific to each activity
 */
public class Activity {

    private int id;
    private int value;

    /**Activity constructor
     * @param id activity identifier
     * @param value amount of activity performed
     * @author ohussein
     */
    public Activity(int id, int value){
        this.id = id;
        this.value = value;
    }

    /**Getter for Id
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**Getter for Value
     *
     * @return value
     */
    public int getValue() {
        return value;
    }

    /**Setter for Value
     *
     * @param value
     */
    public void setValue(int value) {
        this.value = value;
    }
    /**Setter for id
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
}
