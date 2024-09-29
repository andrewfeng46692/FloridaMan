package org.openjfx;

import java.util.ArrayList;
import java.util.Collections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.scene.Parent;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;


public class GameViewController {
    @FXML
    private Label questionLabel;
    @FXML
    private RadioButton option1, option2, option3, option4;
    @FXML
    private Label correctCountLabel, incorrectCountLabel, percentageLabel, feedbackLabel, gameOverLabel;
    @FXML
    private Button submitButton, replayButton, exitButton;
    private ToggleGroup answerGroup;
    private FloridaManGame game = new FloridaManGame(); // Use the instance of FloridaManGame
    private boolean gameOver = false;

    public GameViewController() {
    }

    @FXML
    public void initialize() {
        this.answerGroup = new ToggleGroup();
        this.option1.setToggleGroup(this.answerGroup);
        this.option2.setToggleGroup(this.answerGroup);
        this.option3.setToggleGroup(this.answerGroup);
        this.option4.setToggleGroup(this.answerGroup);

        feedbackLabel.setVisible(false);
        gameOverLabel.setVisible(false);
        replayButton.setVisible(false);
        exitButton.setVisible(false);

        // Initialize the game when the controller starts
        game.initializeGame();
        loadQuestion();

    }

    private void loadQuestion() {
        feedbackLabel.setVisible(false);  // Hide feedback

        // Check if there are more headlines and if a valid question is loaded
        if (game.getCurrentHeadline() != null) {
            // Load the current headline and its options
            Headline currentHeadline = game.getCurrentHeadline();

            questionLabel.setText(currentHeadline.getStory());

            ArrayList<String> options = new ArrayList<>();
            Collections.addAll(options, currentHeadline.getOptions());
            options.add(currentHeadline.getKeyword());
            Collections.shuffle(options);  // Shuffle the options

            option1.setText(options.get(0));
            option2.setText(options.get(1));
            option3.setText(options.get(2));
            option4.setText(options.get(3));

            submitButton.setText("Submit");

        } else {
            // Game over: show Game Over screen
            showGameOver();
        }
    }

    @FXML
    private void handleSubmitAnswer() {
        // Check if the game is over
        if (gameOver) {
            game.resetGame();  // Reset if the game is over
        } else {
            // If the submit button is showing "Submit", validate the answer
            if (submitButton.getText().equals("Submit")) {
                RadioButton selectedOption = (RadioButton) answerGroup.getSelectedToggle();
                if (selectedOption == null) {
                    feedbackLabel.setText("Please select an answer.");
                    feedbackLabel.setTextFill(Color.BLACK);
                    feedbackLabel.setVisible(true);
                } else {
                    // Validate the selected answer
                    String userAnswer = selectedOption.getText();
                    boolean isCorrect = game.checkGuess(userAnswer);

                    // Provide feedback to the user (show "Correct!" or "Wrong!")
                    feedbackLabel.setText(isCorrect ? "Correct!" : "Wrong!");
                    feedbackLabel.setTextFill(isCorrect ? Color.GREEN : Color.RED);
                    feedbackLabel.setVisible(true);

                    // Update player stats and change the button text to "Next"
                    updatePlayerStats();
                    submitButton.setText("Next");
                }
            } else if (submitButton.getText().equals("Next")) {

                // Load the next question if "Next" is clicked
                game.loadNextQuestion();
                loadQuestion();

                // Reset the button text back to "Submit"
                submitButton.setText("Submit");

                // Hide feedback label and clear selected option
                feedbackLabel.setVisible(false);  // Hide feedback when answering a new question
                answerGroup.selectToggle(null);   // Deselect the current selection
            }
        }
    }


    // Update the player stats in the UI
    private void updatePlayerStats() {
        int correctGuesses = game.getCorrectGuesses();
        int incorrectGuesses = game.getIncorrectGuesses();
        double percentage = game.getPercentageCorrect();

        correctCountLabel.setText("Correct: " + correctGuesses);
        incorrectCountLabel.setText("Incorrect: " + incorrectGuesses);
        correctCountLabel.setTextFill(Color.GREEN);
        incorrectCountLabel.setTextFill(Color.RED);

        percentageLabel.setText(String.format("Correct Percentage: %.2f%%", percentage));

        if (percentage <= 60.0) {
            percentageLabel.setTextFill(Color.RED);
        } else if (percentage <= 75.0) {
            percentageLabel.setTextFill(Color.YELLOW);
        } else {
            percentageLabel.setTextFill(Color.GREEN);
        }
    }

    private void showGameOver() {
        gameOver = true;
        gameOverLabel.setVisible(true);
        replayButton.setVisible(true);
        exitButton.setVisible(true);

        // Hide the game-related buttons
        questionLabel.setVisible(false);
        submitButton.setVisible(false);
        option1.setVisible(false);
        option2.setVisible(false);
        option3.setVisible(false);
        option4.setVisible(false);
    }

    @FXML
    private void handleReplay() throws IOException {
        System.out.println("Replay button clicked! Returning to the start screen.");

        // Optional: Reset the game state if needed
        game.resetGame();  // Assuming resetGame is defined in FloridaManGame

        // Load the start screen FXML (start.fxml)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("start.fxml"));
        Parent root = loader.load();

        // Get the current stage and set the scene back to the start screen
        Stage stage = (Stage) replayButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Florida Man Game - Start");
        stage.show();
    }


    @FXML
    private void handleQuit() {
        Platform.exit();  // Close the entire application
    }
}