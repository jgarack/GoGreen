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
     * Description of the achievement.
     */
    private String description;


    /**
     * Constructs an achievment.
     *
     * @param nameAcheived name of the achievement
     * @param isAchieved   state of achievement
     * @param descr        the descrption
     *                     of the achievement.
     */
    public Achievement(final String nameAcheived, final boolean isAchieved,
                       final String descr) {
        this.name = nameAcheived;
        this.achieved = isAchieved;
        this.description = descr;
    }

    /**
     * Checks if achievement is achieved.
     *
     * @return true iff achievement is achieved.
     */
    public boolean isAchieved() {
        return achieved;
    }

    /**
     * Sets achieved to the achievement.
     *
     * @param isAchieved the boolean to be set.
     */
    public void setAchieved(final boolean isAchieved) {
        this.achieved = isAchieved;
    }

    /**
     * Gets the name of the achievement.
     *
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets a name to the achievement.
     *
     * @param achName the name to be set.
     */
    public void setName(final String achName) {
        this.name = achName;
    }

    /**
     * Returns the description of an achievement.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets a new description.
     *
     * @param achDescription to be set.
     */
    public void setDescription(final String achDescription) {
        this.description = achDescription;
    }

    /**
     * Checks for equalness.
     * @param object object to compare
     * @return true if equal.
     */
    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Achievement that = (Achievement) object;
        return achieved == that.achieved && name.equals(that.name)
                && description.equals(that.description);
    }

    /**
     * Checkstyle made me do it.
     * @return the super hashcode
     */
    public int hashCode() {
        return 0;
    }
}
