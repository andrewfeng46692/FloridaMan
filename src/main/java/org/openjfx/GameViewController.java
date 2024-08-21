package org.openjfx;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameViewController {

    @FXML
    private Label storyLabel;

    @FXML
    public void initialize() {
        storyLabel.setText("Here's your first Florida Man story...");
    }
}