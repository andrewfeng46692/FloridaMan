package org.openjfx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;

public class GameViewController {

    @FXML
    private Button quitButton;
    @FXML
    private Label percentageLabel;
    @FXML
    private Label incorrectCountLabel;
    @FXML
    private Label correctCountLabel;
    @FXML
    private Label questionLabel;
    @FXML
    private RadioButton option1, option2, option3, option4;
    @FXML
    private Label feedbackLabel;  // Feedback label
    @FXML
    private Button submitButton;

    private ToggleGroup answerGroup;

    private final FloridaManGame game = new FloridaManGame();  // Game logic manager
    private boolean gameOver = false;  // To track if the game is over

    @FXML
    public void initialize() {
        // Group radio buttons together
        answerGroup = new ToggleGroup();
        option1.setToggleGroup(answerGroup);
        option2.setToggleGroup(answerGroup);
        option3.setToggleGroup(answerGroup);
        option4.setToggleGroup(answerGroup);

        feedbackLabel.setVisible(false);  // Hide feedback label at the start

        // Load the first question
        loadQuestion();
    }

    private void loadQuestion() {
        // Hide the feedback label when loading a new question
        feedbackLabel.setVisible(false);

        // Check if the game is over
        if (game.getCurrentHeadline() == null) {
            questionLabel.setText("Game Over! No more questions.");
            submitButton.setText("Play Again");
            gameOver = true;
            return;
        }

        // Load the next question from the game logic
        Headline currentHeadline = game.getCurrentHeadline();
        questionLabel.setText(currentHeadline.getStoryWithBlanks());

        // Combine correct and incorrect options, then shuffle them
        ArrayList<String> allOptions = new ArrayList<>();
        Collections.addAll(allOptions, currentHeadline.getOptions());  // Add incorrect options
        allOptions.add(currentHeadline.getKeyword());  // Add the correct option
        Collections.shuffle(allOptions);  // Shuffle the list to randomize the order

        // Set the shuffled options to the radio buttons
        option1.setText(allOptions.get(0));
        option2.setText(allOptions.get(1));
        option3.setText(allOptions.get(2));
        option4.setText(allOptions.get(3));

        submitButton.setText("Submit");  // Reset the button to "Submit"
    }

    @FXML
    private void handleSubmitAnswer() {
        if (gameOver) {
            // Reset the game if it is over
            resetGame();
            return;
        }

        // Get the selected option
        RadioButton selectedOption = (RadioButton) answerGroup.getSelectedToggle();
        if (selectedOption == null) {
            feedbackLabel.setText("Please select an answer.");
            feedbackLabel.setTextFill(Color.BLACK);
            feedbackLabel.setVisible(true);  // Show the feedback label
            return;
        }

        String userAnswer = selectedOption.getText();
        boolean isCorrect = game.checkGuess(userAnswer);

        // Update the feedback label based on whether the guess was correct or not
        feedbackLabel.setText(isCorrect ? "Correct!" : "Wrong!");
        feedbackLabel.setTextFill(isCorrect ? Color.GREEN : Color.RED);
        feedbackLabel.setVisible(true);  // Show the feedback label

        // Show the selected word in the headline
        questionLabel.setText(game.getCurrentHeadline().getStory().replace(game.getCurrentHeadline().getKeyword(), selectedOption.getText()));

        // Update player stats (correct guesses, incorrect guesses, percentage)
        updatePlayerStats();

        // Reset the selected radio button (clear selection)
        answerGroup.selectToggle(null);  // Deselect any selected radio button

        // Change the submit button text to "Next"
        submitButton.setText("Next");

        // Load the next question or show "Game Over"
        game.loadNextQuestion();
        loadQuestion();
    }
    private void updatePlayerStats() {
        // Get the game statistics
        int correctGuesses = FloridaManGame.getCorrectGuesses();
        int incorrectGuesses = FloridaManGame.getIncorrectGuesses();
        double percentage = FloridaManGame.getPercentageCorrect();

        // Update the labels for correct/incorrect count
        correctCountLabel.setText("Correct: " + correctGuesses);
        incorrectCountLabel.setText("Incorrect: " + incorrectGuesses);

        // Color the correct/incorrect count labels
        correctCountLabel.setTextFill(Color.GREEN);
        incorrectCountLabel.setTextFill(Color.RED);

        // Update the percentage label and set color based on performance
        percentageLabel.setText(String.format("Correct Percentage: %.2f%%", percentage));
        if (percentage <= 60) {
            percentageLabel.setTextFill(Color.RED);
        } else if (percentage <= 75) {
            percentageLabel.setTextFill(Color.YELLOW);
        } else {
            percentageLabel.setTextFill(Color.GREEN);
        }
    }

    private void resetGame() {
        // Reset the game state and reload the first question
        game.initializeGame();
        loadQuestion();
        gameOver = false;
        feedbackLabel.setVisible(false);  // Hide feedback on reset
    }

    @FXML
    private void handleQuit() {
        // Close the application window when Quit is pressed
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }
}