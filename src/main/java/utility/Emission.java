package utility;

/**
 * emission object for storing co2 emissions.
 * @author Omar Hussein
 */
public class Emission {
    /**
     * String containing CO2 emission.
     */
    private String desisions;

    public Emission() {
    }
    /**
     * Class constructor.
     * @param gas emissions
     */
    public Emission(final String gas) {
        this.desisions = gas;
    }
    /**
     * Getter method.
     * @return emission co2 value
     */
    public String getEmission() {
        return desisions;
    }
    /**Setter method.
     * @param gas emission co2 value
     */
    public void setEmission(final String gas) {
        this.desisions = gas;
    }

    /**
     * Converts carbon emissions to an int.
     * @return Integer value of emissions
     */
    public int getNum() {
        desisions.replace("kg", "");
        return Integer.parseInt(desisions.trim());
    }


}
