package org.openjfx;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
        Parent root = FXMLLoader.load(getClass().getResource("gameQuestions.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        Stage stage = (Stage) startButton.getScene().getWindow();
        stage.setTitle("scene");
        stage.setScene(scene);
        stage.show();
    }
}
