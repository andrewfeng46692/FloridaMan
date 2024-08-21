package org.openjfx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class WelcomeMenuController {
    @FXML
    private Label gameTitleLabel;

    @FXML
    private Button startButton;

    @FXML
    public void initialize() {
        gameTitleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 61");
        gameTitleLabel.setText("The 100% grade Florida Man Game");
    }

    @FXML
    AnchorPane gamePane;

    @FXML
    private void startButton(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ViewGame.fxml"));
        gamePane.getChildren().setAll(pane);
    }


}
