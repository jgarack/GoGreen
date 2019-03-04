package features;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import utility.Activity;
import utility.HttpRequestHandler;
import java.io.BufferedReader;

/**Performs a http post request to server to
 * calculate points for vegetarian meal.
 */
public class VegetarianMeal {
    /**
     * Server Domain.
     */
    private final String domain = "http://localhost:8080";
    /**
     * value to be calculated.
     */
    private int value;

    /**
     * Constructor for VegetarianMeal.
     * @param userValue Text field value
     */
    public VegetarianMeal(final String userValue) {
        this.value =
                Integer.parseInt(userValue.trim());
    }

    /**
     * Getter for value.
     * @return raw value
     */
    public int getValue() {
        return value;
    }

    /**
     * Setter for value.
     * @param raw value without calculation
     */
    public void setValue(final int  raw) {
        this.value = raw;
    }


    /** Handles request for Vegetarian meal points from server.
//     *@param event Clicking on calculate point button
     *@return returns points
     *@author ohussein
     */
    public int calculatePoints() {

//        checkForm();
        try {
            BufferedReader httpBody =
                    HttpRequestHandler.reqPost(domain
                            + "/points", new Activity(1, this.getValue()));
            Alert displayResponse = new Alert(Alert.AlertType.CONFIRMATION);
            displayResponse.setTitle("Good Job!");
            displayResponse.setContentText("Go Green!");
            displayResponse.showAndWait();
            String con = HttpRequestHandler.resLog(httpBody, null);
            System.out.println(con);
            return jsonCon(con);
            } catch (Exception e) {

            exceptionHandler(e);

        }
        return 0;
    }

 public static int jsonCon(final String con) throws Exception {
     ObjectMapper mapper = new ObjectMapper();
     mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
             false);
     JsonNode em = mapper.readValue(con, JsonNode.class);
     int ret = (int)Math.ceil(Double.parseDouble(em.get("decisions").
             get("carbon").
             get("description").textValue().
             replace("kg", "").trim()));
     return ret;
    }
    /**Helper method that handles exceptions.
     *@param e exception that was thrown
     */
    protected void exceptionHandler(final Exception e) {
        Alert statusCodeError = new Alert(Alert.AlertType.ERROR);
        statusCodeError.setTitle(e.getMessage());
//        statusCodeError.setContentText("See terminal for stacktrace.");
        statusCodeError.setContentText(e.toString());
        statusCodeError.showAndWait();
    }

    /**
     *Converts object to a String representation.
     * @return String representation of object
     */
    @Override
    public String toString() {
        return "VegetarianMeal{"
                + "value=" + value
                + '}';
    }
}
