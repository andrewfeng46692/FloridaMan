package org.openjfx;

public class FloridaManGame {
    private static int correctGuesses;
    private static int incorrectGuesses;
    private static double percentageCorrect;
    private static int questionsLeft;
    private static Headline currentHeadline;

    public static void initializeGame() {
        HeadlineBank.shuffleHeadlines();
        loadNextQuestion();
    }
    public static void loadNextQuestion() {
        currentHeadline = HeadlineBank.getNextHeadline();
    }
    public static boolean checkGuess(String userGuess) {
        if (userGuess == null){ return false}

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

}
