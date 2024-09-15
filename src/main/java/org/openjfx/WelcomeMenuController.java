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
    private void HandleStartButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("gameQuestions.fxml")));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        Stage stage = (Stage) startButton.getScene().getWindow();
        stage.setTitle("scene");
        stage.setScene(scene);
        stage.show();
    }

}
