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
    private Label welcomeLabel;

    @FXML
    private Button startButton;

    @FXML
    AnchorPane gamePane;

    @FXML
    public void initialize() {
        welcomeLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 61");
        welcomeLabel.setText("The 100%(now only 95%) grade Florida Man Game");
        startButton.setText("Start Game");
    }

    @FXML
    public void HandleStartButtonAction(javafx.event.ActionEvent actionEvent) throws IOException {
        System.out.println("Start button clicked");

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("gameQuestions.fxml")));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());

        Stage stage = (Stage) startButton.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Game Questions");
        stage.show();
    }
}
