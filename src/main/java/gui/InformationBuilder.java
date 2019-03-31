package gui;

import animatefx.animation.Pulse;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.util.Duration;
import org.controlsfx.control.PopOver;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.GlyphFontRegistry;

/**
 * Class that builds information buttons.
 */
public class InformationBuilder {
    /**
     * Duration value.
     */
    private static final double DURATION = 0.5;
    /**
     * Font size value.
     */
    private static final int FONT_SIZE = 20;

    /**
     * Helper tool.
     */
    private PopOver popOver;


    /**
     * Default constructor.
     */
    public InformationBuilder() {
        this.popOver = new PopOver();
    }

    /**
     * Adds information icon to search bar.
     * @param informativeLabel the information
     *                         message
     * @param message the actual message
     */
    public void
    addInformationIconToSearchBox(final Label informativeLabel,
                                  final String message) {
        informativeLabel.setBackground(Background.EMPTY);
        informativeLabel.setStyle("-fx-font-family: 'FontAwesome'");
        informativeLabel
                .setGraphic(GlyphFontRegistry
                        .font("FontAwesome")
                        .create(FontAwesome.Glyph.INFO_CIRCLE)
                        .size(FONT_SIZE));
        informativeLabel
                .setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(final MouseEvent event) {
                        Label infoLabel = new Label(message);
                        infoLabel.setId("infoLabel");
                        popOver = new PopOver(infoLabel);
                        popOver.setFadeInDuration(Duration.seconds(DURATION));
                        popOver.setId("infoPopOver");
                        popOver.show(informativeLabel);
                        new Pulse(infoLabel).play();
                    }
                });
        informativeLabel.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                popOver.hide();
            }
        });
    }
}