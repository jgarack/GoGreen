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

    /**
     * Constructs an achievment.
     * @param nameAcheived name of the achievement
     * @param isAchieved state of achievement
     */
    public Achievement(String nameAcheived, boolean isAchieved) {
        this.name = nameAcheived;
        this.achieved = isAchieved;
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
}
