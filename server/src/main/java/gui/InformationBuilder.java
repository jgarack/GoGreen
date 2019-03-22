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

public class InformationBuilder {
    private PopOver popOver;

    public InformationBuilder(){
        this.popOver = new PopOver();
    }

    /**
     * Adds information icon to search bar.
     */
    public void addInformationIconToSearchBox(Label informativeLabel, String message) {
        informativeLabel.setBackground(Background.EMPTY);
        informativeLabel.setStyle("-fx-font-family: 'FontAwesome'");
        informativeLabel
                .setGraphic(GlyphFontRegistry
                        .font("FontAwesome")
                        .create(FontAwesome.Glyph.INFO_CIRCLE)
                        .size(20));
        informativeLabel
                .setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(final MouseEvent event) {
                        Label infoLabel = new Label(message);
                        infoLabel.setId("infoLabel");
                        popOver = new PopOver(infoLabel);
                        popOver.setFadeInDuration(Duration.seconds(0.5));
                        popOver.setId("infoPopOver");
                        popOver.show(informativeLabel);
                        new Pulse(infoLabel).play();
                    }
                });
        informativeLabel.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                popOver.hide();
            }
        });
    }
}
