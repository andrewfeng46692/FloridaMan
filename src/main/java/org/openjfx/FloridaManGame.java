package org.openjfx;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;

public class FloridaManGame extends Application {
    private static int correctGuesses = 0;
    private static int incorrectGuesses = 0;
    private static double percentageCorrect = 0.0;
    private static int questionsLeft;
    private static Headline currentHeadline;
    private HeadlineBank headlineBank = new HeadlineBank();  // Instance of HeadlineBank

    @Override
    public void start(Stage stage) throws Exception {
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
        loadHeadlines();
        headlineBank.shuffleHeadlines();
        loadNextQuestion();
        questionsLeft = headlineBank.size();  // Correct size access
    }

    // Load sample headlines
    private void loadHeadlines() {
        ArrayList<Headline> sampleHeadlines = new ArrayList<>();
        sampleHeadlines.add(new Headline("Florida Man wrestles with a(n) ______.", "alligator", new String[]{"shark", "python", "cat"}));
        sampleHeadlines.add(new Headline("Florida Man steals ______ from the grocery store.", "pizza", new String[]{"apples", "bread", "bananas"}));
        sampleHeadlines.add(new Headline("Florida Man drives through a fast-food restaurant with a ______ on his lap.", "cat", new String[]{"dog", "parrot", "turtle"}));
        sampleHeadlines.add(new Headline("Florida Man arrested for throwing ______ at his neighbor.", "gator", new String[]{"sandwich", "shoe", "brick"}));
        sampleHeadlines.add(new Headline("Florida Man runs from cops while holding a(n) ______.", "iguana", new String[]{"baby", "beer", "pizza"}));
        sampleHeadlines.add(new Headline("Florida Man arrested for trying to ride a(n) ______ down the highway.", "lawnmower", new String[]{"horse", "bicycle", "golf cart"}));
        sampleHeadlines.add(new Headline("Florida Man calls 911 to report stolen ______.", "beer", new String[]{"money", "car", "crocodile"}));
        sampleHeadlines.add(new Headline("Florida Man caught trying to smuggle ______ into a theme park.", "python", new String[]{"iguana", "ferret", "parrot"}));
        sampleHeadlines.add(new Headline("Florida Man breaks into home and tries to cook ______.", "spaghetti", new String[]{"steak", "chicken", "pancakes"}));
        sampleHeadlines.add(new Headline("Florida Man attacked by a(n) ______ while fishing in his backyard.", "crocodile", new String[]{"bird", "snake", "cat"}));

        headlineBank.addHeadlines(sampleHeadlines);
    }

    // Load the next question from the HeadlineBank
    public void loadNextQuestion() {
        if (headlineBank.hasMoreHeadlines()) {
            currentHeadline = headlineBank.getNextHeadline();
        } else {
            currentHeadline = null; // If no more questions, set to null
        }
    }

    // Check the user's guess against the correct keyword
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

    // Update the percentage of correct answers
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

    public Headline getCurrentHeadline() {
        return currentHeadline;
    }

    // Setters for game statistics
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
}