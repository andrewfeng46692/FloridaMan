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
    //Static variables
    private static int correctGuesses = 0;
    private static int incorrectGuesses = 0;
    private static double percentageCorrect = 0.0;
    private static int questionsLeft;
    private static Headline currentHeadline;


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

        HeadlineBank.clearHeadlines();
        loadHeadlines();
        HeadlineBank.shuffleHeadlines();
        questionsLeft = HeadlineBank.size();
        loadNextQuestion();  // Load the first question
    }

    //A method to initialize the Headline objects
    private void loadHeadlines() {
        new Headline("Florida Man steals pizza from the grocery store.", "pizza", new String[]{"apples", "bread", "bananas"});
        new Headline("Florida Man drives through a fast-food restaurant with a cat on his lap.", "cat", new String[]{"dog", "parrot", "turtle"});
        new Headline("Florida Man arrested for throwing gator at his neighbor.", "gator", new String[]{"sandwich", "shoe", "brick"});
        new Headline("Florida Man runs from cops while holding a(n) iguana.", "iguana", new String[]{"baby", "beer", "pizza"});
        new Headline("Florida Man arrested for trying to ride a(n) lawnmower down the highway.", "lawnmower", new String[]{"horse", "bicycle", "golf cart"});
        new Headline("Florida Man calls 911 to report stolen beer.", "beer", new String[]{"money", "car", "crocodile"});
        new Headline("Florida Man caught trying to smuggle python into a theme park.", "python", new String[]{"iguana", "ferret", "parrot"});
        new Headline("Florida Man breaks into home and tries to cook spaghetti.", "spaghetti", new String[]{"steak", "chicken", "pancakes"});
        new Headline("Florida Man attacked by a(n) crocodile while fishing in his backyard.", "crocodile", new String[]{"bird", "snake", "cat"});
        new Headline("Florida Man killed by a(n) baby lion while visiting the zoo.", "baby lion", new String[]{"taco", "snake", "Texas ranger"});



    }

    //Should set the loaded question to the next available question
    //A method to “load” the next question (should call the method from HeadlineBank class)
    public void loadNextQuestion() {
        currentHeadline = HeadlineBank.getNextHeadline();//current headline will be null when HeadlineBank is empty
        questionsLeft--;
    }

    //A method to check the user’s guess against the correct keyword
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
        initializeGame();
        System.out.println("Game has been reset.");
    }
}
