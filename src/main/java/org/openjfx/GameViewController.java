package org.openjfx;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GameViewController {

    @FXML
    private Label storyLabel;

    @FXML
    private Label headlineLabel;

    @FXML
    private Button option1Button, option2Button, option3Button, option4Button, submitButton;

    @FXML
    private Label correctLabel, incorrectLabel, percentageLabel, questionsLeft;

    @FXML
    public void initialize() {
        // Directly display the first loaded headline, no need to call loadNextQuestion
        displayCurrentQuestion();
        updateStats();
    }

    private void displayCurrentQuestion() {
        Headline headline = FloridaManGame.loadNextQuestion();
        if (headline != null) {
            headlineLabel.setText(headline.removedHeadline());
            option1Button.setText(headline.getOptions().get(0));
            option2Button.setText(headline.getOptions().get(1));
            option3Button.setText(headline.getOptions().get(2));
            option4Button.setText(headline.getOptions().get(3));
        } else {
            headlineLabel.setText("Game Over!");
        }
    }
    @FXML
    public void submitGuess() {
        String selectedOption = "";

        boolean isCorrect = FloridaManGame.checkGuess(selectedOption);

        if (isCorrect) {
            headlineLabel.setText("Correct!");
        } else {
            headlineLabel.setText("Wrong! The correct word was: " + FloridaManGame.getLoadedHeadline().getKeyword());
        }

        // Load the next question for display
        FloridaManGame.loadNextQuestion();
        displayCurrentQuestion();
        updateStats();
    }

    private void updateStats() {
        correctLabel.setText("Correct: " + FloridaManGame.getCorrectGuesses());
        incorrectLabel.setText("Incorrect: " + FloridaManGame.getIncorrectGuesses());

        double percentage = FloridaManGame.getPercentageCorrect();
        percentageLabel.setText("Correct %: " + String.format("%.2f", percentage) + "%");

        if (percentage <= 60) {
            percentageLabel.setStyle("-fx-text-fill: red;");
        } else if (percentage <= 75) {
            percentageLabel.setStyle("-fx-text-fill: yellow;");
        } else {
            percentageLabel.setStyle("-fx-text-fill: green;");
        }
    }
}
}