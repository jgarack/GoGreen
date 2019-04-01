package gui;

import animatefx.animation.GlowText;
import animatefx.animation.Pulse;
import exceptions.ServerStatusException;
import features.Feature;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.GlyphFontRegistry;
import utility.MainHandler;

import java.io.IOException;

/**
 * Controller for the home page.
 */
public class HomeController {
    /**
     * The almighty i^2.
     */
    private static final int MINUS = -1;

    /**
     * Alert text.
     */
    private static final String YOU_NEED_TO_FILL_A_NUMBER
            = "You need to fill a number!";
    /**
     * Magic number 0.7.
     */
    private static final double PROGRESS_BAR_INIT_VAL = 0.7;
    /**
     * Magic number 300.
     */
    private static final int THREE_HUNDRED = 300;
    /**
     * Magic number 250.
     */
    private static final int TWO_HUNDRED_FIFTY = 250;

    /**
     * Magic number 3.
     */
    private static final int THREE = 3;
    /**
     * Magic number 4.
     */
    private static final int FOUR = 4;
    /**
     * Magic number 5.
     */
    private static final int FIVE = 5;
    /**
     * The builder used to build alerts for this handler.
     */
    private static final AlertBuilder ALERT_BUILDER = new AlertBuilder();

    /**
     * Handler for handling main operations.
     */
    public MainHandler handler = new MainHandler("http://localhost:8080");

    /**
     * Data about vegetarian meals consumed
     * that is to be retrieved from database.
     */
    private int vegetarianMeals = 0;
    /**
     * Data about the times that a bicycle
     * has been used that is to be retrieved from database.
     */
    private int bicycleUsed = 0;
    /**
     * Data about the amount of times
     * local produce has been consumed.
     */
    private int localUsage = 0;
    /**
     *Data about the amount of bus rides taken.
     */
    private int busUsage = 0;
    /**
<<<<<<< HEAD
     *Data about the Solar Panel usage.
=======
     *Data about the amount of bus rides taken.
>>>>>>> f3d82b33822227fdba0c8099d0d68ac603991d7e
     */
    private int solarUsage = 0;
    /**
     *Data about the amount of degrees reduced.
     */
    private int tempRed = 0;
    /**
     * Data about the points of the user.
     */
    private int pointsEarned = 0;
    /**
     * Bound to the text field linked with vegetarian meals.
     */
    @FXML
    private TextField vegMeals;
    /**
     * Bound to the text field about the usage of bicycle.
     */
    @FXML
    private TextField bicycleUsage;
    /**
     * Bound to the text field linked with buying local produce.
     */

    @FXML
    private TextField localProduce;
    /**
     * Bound to the text field linked with using public transport.
     */
    @FXML
    private TextField busCar;
    /**
     * Bound to the text field linked with installing solar panel.
     */
    @FXML
    private TextField solarPanel;
    /**
     * Bound to the text field linked with installing solar panel.
     */
    @FXML
    private TextField lowerTemps;

    /**
     * Bound to the label about the vegetarian meals
     * (used to show progress).
     */
    @FXML
    private Label vegMealsEaten;
    /**
     * Bound to the bicycle label - used to show progress.
     */
    @FXML
    private Label bicycleUsedLabel;

    /**
     * Bound to the bicycle button.
     */
    @FXML
    private Button increaseBicycleUsageBtn;
    /**
     * Bound to the veggie meals button.
     */
    @FXML
    private Button increaseVegetarianMealsBtn;
    /**
     * Bound to the local produce button.
     */
    @FXML
    private Button increaseLocalProduceBtn;
    /**
     * Bound to the bus usage button.
     */
    @FXML
    private Button increaseBusUsageBtn;
    /**
     * Bound to the bus usage button.
     */
    @FXML
    private Button increaseLowerTempsBtn;
    /**
     * Bound to the bus usage button.
     */
    @FXML
    private Button increaseSolarPanelsBtn;

    /**
     * Bound to the points earned label.
     */
    @FXML
    private Label pointsEarnedLabel;

    /**
     * Triggered upon initialization of the home scene.
     */
    @FXML
    public void initialize() {
        //progressBarGreen.setProgress(PROGRESS_BAR_INIT_VAL);
        try {
            pointsEarnedLabel
                    .setText("Points earned: " + handler.getTotalScore());
            FontAwesome.Glyph plusGlyph = FontAwesome.Glyph.PLUS;
            setFontAwesomeGlyphToBtn(increaseBicycleUsageBtn,
                    plusGlyph);
            setFontAwesomeGlyphToBtn(increaseBusUsageBtn,
                    plusGlyph);
            setFontAwesomeGlyphToBtn(increaseVegetarianMealsBtn,
                    plusGlyph);
            setFontAwesomeGlyphToBtn(increaseLocalProduceBtn,
                    plusGlyph);
            setFontAwesomeGlyphToBtn(increaseSolarPanelsBtn,
                    plusGlyph);
            setFontAwesomeGlyphToBtn(increaseLowerTempsBtn,
                    plusGlyph);




        } catch (ServerStatusException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Used to increase the amount of vegetarian meals filled in the text field.
     * @param event The fired event when the increase button is pressed.
     */
    @FXML
    protected void increaseVegetarianMeals(final ActionEvent event) {
        if (MainHandler.tryParseInt(vegMeals.getText())) {


            Feature meal = new Feature(Integer.parseInt(vegMeals.getText()), 1);
            System.out.println(meal.toString());

            this.vegetarianMeals = handler
                    .updateVegMeal(Integer.parseInt(vegMeals.getText()));
            setPointsEarned();

            alert();

            this.vegMeals.setText("");

        } else {
            ALERT_BUILDER
                    .formEntryWarning(vegMealsEaten.getText(),
                            YOU_NEED_TO_FILL_A_NUMBER);
        }
    }

    /**
     * Used to increase the amount of times a bicycle
     * that has been used filled in the text field.
     * @param event The fired event when the increase button is pressed.
     */
    @FXML
    protected void increaseBicycleUsage(final ActionEvent event) {
        if (MainHandler.tryParseInt(bicycleUsage.getText())) {
            Feature bike = new Feature(Integer
                    .parseInt(bicycleUsage.getText()), 2);
            System.out.println(bike.toString());
            this.bicycleUsed = handler
                    .updateBike(Integer
                            .parseInt(bicycleUsage.getText()));

            setPointsEarned();

            alert();

            this.bicycleUsage.setText("");

        } else {
            ALERT_BUILDER
                    .formEntryWarning(bicycleUsage.getText(),
                            YOU_NEED_TO_FILL_A_NUMBER);
        }
    }

    /**
     * Linked to the button to increase
     * the amount of local produced a user has bought.
     * @param event The event fired upon clicking the button
     */
    @FXML
    protected void increaseLocalProduce(final ActionEvent event) {
        if (MainHandler.tryParseInt(localProduce.getText())) {
            Feature local = new Feature(Integer
                    .parseInt(localProduce.getText()), THREE);
            System.out.println(local.toString());
            this.localUsage = handler
                    .updateLocal(Integer.parseInt(localProduce.getText()));

            setPointsEarned();

            alert();

            this.localProduce.setText("");

        } else {
            ALERT_BUILDER
                    .formEntryWarning(localProduce.getText(),
                            YOU_NEED_TO_FILL_A_NUMBER);
        }
    }

    /**
     * Linked to the button to increase
     * the amount of times a user has used a bus.
     * @param event The event fired
     *              upon clicking the button
     */
    @FXML
    protected void increaseBusUsage(final ActionEvent event) {

        if (MainHandler.tryParseInt(busCar.getText())) {
            Feature bus = new Feature(Integer
                    .parseInt(busCar.getText()), FOUR);
            System.out.println(busCar.toString());
            this.busUsage = handler
                    .updateBus(Integer.parseInt(busCar.getText()));

            setPointsEarned();

            alert();

            this.busCar.setText("");

        } else {
            ALERT_BUILDER
                    .formEntryWarning(localProduce.getText(),
                            YOU_NEED_TO_FILL_A_NUMBER);
        }

    }

    /**
     * Linked to the button to increase
     * the amount of KWH per month.
     * @param event The event fired
     *              upon clicking the button
     */
    @FXML
    protected void increaseSolar(final ActionEvent event) {

        if (MainHandler.tryParseInt(solarPanel.getText())) {
            Feature solar = new Feature(Integer
                    .parseInt(solarPanel.getText()), FIVE);
            System.out.println(solarPanel.toString());
            this.solarUsage = handler
                    .updateSolar(Integer.parseInt(solarPanel.getText()));
            if (this.solarUsage != 0) {
                setPointsEarned();
                alert();
            }


            this.solarPanel.setText("");

        } else {
            ALERT_BUILDER
                    .formEntryWarning(localProduce.getText(),
                            YOU_NEED_TO_FILL_A_NUMBER);
        }

    }
    /**
     * Linked to the button to increase
     * lowering home temp month.
     * @param event The event fired
     *              upon clicking the button
     */
    @FXML
    protected void increaseEff(final ActionEvent event) {

        if (MainHandler.tryParseInt(lowerTemps.getText())) {

            System.out.println(lowerTemps.toString());
            this.tempRed = handler
                    .updateHeating(Integer.parseInt(lowerTemps.getText()));

                setPointsEarned();
                alert();



            this.lowerTemps.setText("");

        } else {
            ALERT_BUILDER
                    .formEntryWarning(lowerTemps.getText(),
                            YOU_NEED_TO_FILL_A_NUMBER);
        }

    }



    /**
     *  Makes the points earned label glow.
     * @param c1 The first color
     * @param c2 The second color.
     */
    private void glowPointsEarnedLabel(final Color c1, final Color c2) {
        new GlowText(this.pointsEarnedLabel, c1, c2).play();
    }

    /**
     *  Makes the points earned label glow and pulse.
     * @param c1 The first color
     * @param c2 The second color.
     */
    private void onUpdatePointsEarnedLabel(final Color c1, final Color c2) {
        new Pulse(this.pointsEarnedLabel).play();
        glowPointsEarnedLabel(c1, c2);

    }

    /**
     * Sets font awesome glyph to a button.
     * @param btn The recipient.
     * @param glyph The glyph that is appended.
     */
    private void
        setFontAwesomeGlyphToBtn(final Button btn,
                                          final FontAwesome.Glyph glyph) {
        btn.setStyle("-fx-font-family: 'FontAwesome'");
        btn
                .setGraphic(GlyphFontRegistry
                        .font("FontAwesome")
                        .create(glyph));
    }

    /**
     * helper method for updating points earned.
     */
    private void setPointsEarned() {
        try {
            this.pointsEarned = handler.getTotalScore();
        } catch (IOException | ServerStatusException e) {
            new AlertBuilder().displayException(e);
        }
    }

    /**
     * helper method for alerts to decrease redundancy.
     */
    private void alert() {
        pointsEarnedLabel.setText("Points earned: "
                + this.pointsEarned);

        ALERT_BUILDER
                .showInformationNotification(
                        "Good job! Keep on going greener!");

        onUpdatePointsEarnedLabel(Color.WHITE, Color.DARKRED);

    }
}

