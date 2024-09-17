//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

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
    private static int correctGuesses = 0;
    private static int incorrectGuesses = 0;
    private static double percentageCorrect = 0.0;
    private static int questionsLeft;
    private static Headline currentHeadline;
    private HeadlineBank headlineBank = new HeadlineBank();

    public FloridaManGame() {
    }

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
        this.loadHeadlines();
        this.headlineBank.shuffleHeadlines();
        this.loadNextQuestion();
        questionsLeft = this.headlineBank.size();
    }

    private void loadHeadlines() {
        ArrayList<Headline> sampleHeadlines = new ArrayList();
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
        this.headlineBank.addHeadlines(sampleHeadlines);
    }

    public void loadNextQuestion() {
        if (this.headlineBank.hasMoreHeadlines()) {
            currentHeadline = this.headlineBank.getNextHeadline();
        } else {
            currentHeadline = null;
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
            percentageCorrect = (double)correctGuesses / (double)totalQuestionsAnswered * 100.0;
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
