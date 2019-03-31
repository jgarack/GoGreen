package utility;

/**
 * emission object for storing co2 emissions.
 * @author Omar Hussein
 */
public class Emission {
    /**
     * String containing CO2 emission.
     */
    private String decisions;

    /**
     * Class constructor.
     * @param gas emissions
     */
    public Emission(final String gas) {
        this.decisions = gas;
    }

    /**
     * Getter method.
     * @return emission co2 value
     */
    public String getEmission() {
        return decisions;
    }

    /**Setter method.
     * @param gas emission co2 value
     */
    public void setEmission(final String gas) {
        this.decisions = gas;
    }

    /**
     * Converts carbon emissions to an int.
     * @return Integer value of emissions
     */
    public int getNum() {
        decisions = decisions.replace("kg", "");
        return Integer.parseInt(decisions.trim());
    }

    /**
     * Equals method that checks whether this and another object are the same.
     * @param other The object that is compared to this one.
     * @return Returns true iff the two object are emissions and they have the same gas decisions.
     */
    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof Emission) {
            Emission that = (Emission) other;
            return this.getEmission().equals(that.getEmission());
        }
        return false;
    }

    /**
     * HashCode mock for checkstyle.
     * @return 0
     */
    @Override
    public int hashCode() {
        return 0;
    }
}
