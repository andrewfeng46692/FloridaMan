//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.openjfx;

import java.io.IOException;
import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class WelcomeMenuController {
    @FXML
    private Label welcomeLabel;
    @FXML
    private TextArea instructionTextArea;
    @FXML
    private Button startButton;
    @FXML
    AnchorPane gamePane;

    private final FloridaManGame game = new FloridaManGame();

    public WelcomeMenuController() {
    }

    @FXML
    public void initialize() {
        this.welcomeLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 28");
        this.welcomeLabel.setText("The 100%(now only 95%) Florida Man Game");
        this.instructionTextArea.setText("Instructions: Select the correct word that completes the 'Florida Man' headline from the multiple-choice options provided. Press 'Submit' to confirm your answer.");
        this.startButton.setText("Start Game");
    }

    @FXML
    public void HandleStartButtonAction(ActionEvent actionEvent) throws IOException {
        System.out.println("Start button clicked");

        game.initializeGame();

        Parent root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("gameQuestions.fxml")));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("styles.css")).toExternalForm());
        Stage stage = (Stage) this.startButton.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Game Questions");
        stage.show();
    }
}
