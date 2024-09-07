package org.openjfx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.layout.StackPane;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;

public class FloridaManGame extends Application {
    private static int correctGuesses;
    private static int incorrectGuesses;
    private static double percentageCorrect;
    private static int questionsLeft;
    private static Headline currentHeadline;

    public static void initializeGame() {
        HeadlineBank.shuffleHeadlines();
        loadNextQuestion();

    }
    public static Headline loadNextQuestion() {
        currentHeadline = HeadlineBank.getNextHeadline();
        return HeadlineBank.getNextHeadline();
    }
    public static boolean checkGuess(String userGuess) {
        if (userGuess == null){ return false;}

        boolean isCorrect = userGuess.equals(currentHeadline.getKeyword());
        if (isCorrect){
            correctGuesses++;
        }
        else{
            incorrectGuesses++;
        }
        return isCorrect;
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
    public static void setCurrentHeadline(Headline currentHeadline) {
        FloridaManGame.currentHeadline = currentHeadline;
    }
    public static void setCorrectGuesses(int correctGuesses) {
        FloridaManGame.correctGuesses = correctGuesses;
    }
    public static void setIncorrectGuesses(int incorrectGuesses) {
        FloridaManGame.incorrectGuesses = incorrectGuesses;
    }
    public static void setPercentageCorrect(double percentageCorrect) {
        FloridaManGame.percentageCorrect = percentageCorrect;
    }



    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("FloridaManGame");
        Button startButton = new Button("Start");

        Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        stage.setTitle("scene");
        stage.setScene(scene);
        stage.show();
    }
}
