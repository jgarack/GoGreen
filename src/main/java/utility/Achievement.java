package utility;

/**
 * Private achievement class.
 */
public class Achievement {
    /**
     * Name of the achievement.
     */
    private String name;

    /**
     * Indicates whether it is achieved.
     */
    private boolean achieved;

    private String description;



    /**
     * Constructs an achievment.
     * @param nameAcheived name of the achievement
     * @param isAchieved state of achievement
     */
    public Achievement(String nameAcheived, boolean isAchieved, String descr) {
        this.name = nameAcheived;
        this.achieved = isAchieved;
        this.description = descr;
    }

    /**
     * Checks if achievement is achieved.
     * @return true iff achievement is achieved.
     */
    public boolean isAchieved() {
        return achieved;
    }

    /**
     * Sets achieved to the achievement.
     * @param achieved the boolean to be set.
     */
    public void setAchieved(boolean achieved) {
        this.achieved = achieved;
    }

    /**
     * Gets the name of the achievement.
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets a name to the achievement.
     * @param name the name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the description of an achievement.
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets a new description.
     * @param description to be set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
