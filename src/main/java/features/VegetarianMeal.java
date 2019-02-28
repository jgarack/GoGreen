package features;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import utility.Activity;
import utility.HttpRequestHandler;
import java.io.BufferedReader;

/**Performs a http post request to server to calculate points for vegetarian meal.
 *
 */

public class VegetarianMeal {

    private final String domain = "http://localhost:8080";
    private int value;
//    @FXML
//    private TextField test;

    public VegetarianMeal(TextField userValue){
        this.value = Integer.parseInt(userValue.getText().trim());
    }
    /** Handles request for Vegetarian meal points from server
     *@param event Clicking on calculate point button
     *@author ohussein
     */
    public void calculatePoints(ActionEvent event){

//        checkForm();
        try {
            BufferedReader httpBody = HttpRequestHandler.reqPost(domain + "/points", new Activity(1,value));
            Alert displayResponse = new Alert(Alert.AlertType.CONFIRMATION);
            HttpRequestHandler.resLog(httpBody, null);
            displayResponse.setTitle("Good Job!");
            displayResponse.setContentText("Go Green!");
            displayResponse.showAndWait();
        }
        catch (Exception e){

            exceptionHandler(e);

        }

    }

    /**Helper method checks if field is empty and alerts user if so
     * @author ohussein
     */
//    protected void checkForm() {
//        if (Test.getText().trim().isEmpty()) {
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle("Please Fill In Points");
//            alert.setContentText("No Points Were Entered!");
//            alert.showAndWait();
//        }
//    }

    /**Helper method that handles exceptions
     *
     * @param e exception that was thrown
     */
    protected void exceptionHandler(Exception e){
        Alert statusCodeError = new Alert(Alert.AlertType.ERROR);
        statusCodeError.setTitle(e.getMessage());
//        statusCodeError.setContentText("See terminal for stacktrace.");
        statusCodeError.setContentText(e.toString());
        statusCodeError.showAndWait();
    }

    @Override
    public String toString() {
        return "VegetarianMeal{" +
                "value=" + value +
                '}';
    }
}
