package org.openjfx;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FloridaManGame extends Application {
    private int correctGuesses = 0;
    private int incorrectGuesses = 0;
    private double percentageCorrect = 0.0;
    private int questionsLeft;
    private Headline currentHeadline;


    public void start(Stage stage) throws Exception {

        Parent root = (Parent)FXMLLoader.load((URL)Objects.requireNonNull(this.getClass().getResource("start.fxml")));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(((URL)Objects.requireNonNull(this.getClass().getResource("styles.css"))).toExternalForm());
        stage.setTitle("Florida Man Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void initializeGame() {

        loadHeadlines();
        HeadlineBank.shuffleHeadlines();
        this.questionsLeft = getHeadlineBankSize();  // Initialize the number of questions AFTER loading headlines
        this.loadNextQuestion();  // Load the first question
        System.out.println(currentHeadline.getStory());
    }

    private void loadHeadlines() {
        new Headline("Florida Man steals pizza from the grocery store.", "pizza", new String[]{"apples", "bread", "bananas"});
        new Headline("Florida Man steals pizza from the grocery store.", "pizza", new String[]{"apples", "bread", "bananas"});
        new Headline("Florida Man steals pizza from the grocery store.", "pizza", new String[]{"apples", "bread", "bananas"});
        new Headline("Florida Man steals pizza from the grocery store.", "pizza", new String[]{"apples", "bread", "bananas"});


    }

    public void loadNextQuestion() {
        if (HeadlineBank.hasMoreHeadlines()) {
            currentHeadline = HeadlineBank.getNextHeadline();
            questionsLeft--;
        } else {
            currentHeadline = null;  // Set to null when no more headlines are available
        }
    }

    public boolean checkGuess(String userGuess) {

        if (currentHeadline != null && userGuess != null) {
            boolean isCorrect = userGuess.equalsIgnoreCase(currentHeadline.getKeyword());
            if (isCorrect) {
                ++correctGuesses;
            } else {
                ++incorrectGuesses;
            }

            this.updatePercentageCorrect();
            return isCorrect;
        } else {
            return false;
        }
    }
    private void updatePercentageCorrect() {
        int totalQuestionsAnswered = correctGuesses + incorrectGuesses;
        if (totalQuestionsAnswered > 0) {
            percentageCorrect = (double) correctGuesses / totalQuestionsAnswered * 100.0;
        } else {
            percentageCorrect = 0.0;
        }
    }

    public int getCorrectGuesses() {
        return correctGuesses;
    }
    public int getIncorrectGuesses() {
        return incorrectGuesses;
    }
    public double getPercentageCorrect() {
        return percentageCorrect;
    }
    public int getQuestionsLeft() {
        return questionsLeft;
    }
    public Headline getCurrentHeadline() {
        return currentHeadline;
    }
    public void setQuestionsLeft(int questionsLeft) {
        this.questionsLeft = questionsLeft;
    }
    public void setCurrentHeadline(Headline currentHeadline) {
        this.currentHeadline = currentHeadline;
    }
    public void setCorrectGuesses(int correctGuesses) {
        this.correctGuesses = correctGuesses;
    }
    public  void setIncorrectGuesses(int incorrectGuesses) {
        this.incorrectGuesses = incorrectGuesses;
    }
    public  void setPercentageCorrect(double percentageCorrect) {
        this.percentageCorrect = percentageCorrect;
    }
    public int getHeadlineBankSize() {
        return HeadlineBank.size();  // Get the number of headlines left
    }
    public void resetGame() {
        // Reset game variables (e.g., scores, questions)
        correctGuesses = 0;
        incorrectGuesses = 0;
        percentageCorrect = 0.0;
        HeadlineBank.clearHeadlines();
        initializeGame();
        System.out.println("Game has been reset.");
    }
}
