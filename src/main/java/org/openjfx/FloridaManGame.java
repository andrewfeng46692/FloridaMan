package org.openjfx;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.scene.layout.StackPane;
import javafx.event.EventHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;
import java.awt.*;

public class FloridaManGame extends Application {
    private static int correctGuesses;
    private static int incorrectGuesses;
    private static double percentageCorrect;
    private static int questionsLeft;
    private static Headline currentHeadline;
    private final HeadlineBank headlineBank = new HeadlineBank();  // Create an instance of HeadlineBank


    @Override
    public void start(Stage stage) throws Exception {
        // Load the initial scene from start.fxml
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("start.fxml")));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());


        stage.setTitle("Florida Man Game");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


    public void initializeGame() {
        headlineBank.shuffleHeadlines();
        loadNextQuestion();
        questionsLeft = headlineBank.size();
    }


    public void loadNextQuestion() {
        if (headlineBank.hasNext()) {
            currentHeadline = headlineBank.getNextHeadline();
        } else {
            currentHeadline = null; // If no more questions are available, set to null
        }
    }
    public boolean checkGuess(String userGuess) {
        if (currentHeadline == null || userGuess == null) return false;


        boolean isCorrect = userGuess.equalsIgnoreCase(currentHeadline.getKeyword());
        if (isCorrect) {
            correctGuesses++;
        } else {
            incorrectGuesses++;
        }


        // Update percentage of correct answers
        updatePercentageCorrect();
        return isCorrect;
    }
    private void updatePercentageCorrect() {
        int totalQuestionsAnswered = correctGuesses + incorrectGuesses;
        if (totalQuestionsAnswered > 0) {
            percentageCorrect = (double) correctGuesses / totalQuestionsAnswered * 100;
        } else {
            percentageCorrect = 0.0;
        }
    }


    public static int getCorrectGuesses() {
        return correctGuesses;
    }
    public static int getIncorrectGuesses() {
        return incorrectGuesses;
    }
    public static double getPercentageCorrect() {
        return percentageCorrect;
    }
    public static int getQuestionsLeft() {
        return questionsLeft;
    }
    public static Headline getCurrentHeadline() {
        return currentHeadline;
    }
    public static void setQuestionsLeft(int questionsLeft) {
        FloridaManGame.questionsLeft = questionsLeft;
    }
    public static void setCurrentHeadline(Headline currentHeadline) {FloridaManGame.currentHeadline = currentHeadline;}
    public static void setCorrectGuesses(int correctGuesses) {
        FloridaManGame.correctGuesses = correctGuesses;
    }
    public static void setIncorrectGuesses(int incorrectGuesses) {
        FloridaManGame.incorrectGuesses = incorrectGuesses;
    }
    public static void setPercentageCorrect(double percentageCorrect) {FloridaManGame.percentageCorrect = percentageCorrect;}
}

