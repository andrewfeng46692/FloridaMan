package org.openjfx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public  class HeadlineBank {

    private static ArrayList<Headline> headlines=new ArrayList<>(); // ArrayList of all headlines
    //static


    // Shuffle for randomness
    public static void shuffleHeadlines() {
        Collections.shuffle(headlines);
    }
    public static void clearHeadlines() {
        headlines.clear();  // Clear the list of headlines
    }
    //getters and setters
    public static void addHeadline(Headline headline) {headlines.add(headline);}
    public static void addHeadlines(ArrayList<Headline> headlines) {HeadlineBank.headlines = headlines;}
    public static Headline getNextHeadline() {
        if (hasMoreHeadlines()) {
            // Return and remove the first headline
            return headlines.removeFirst();
        }
        return null;  // No more headlines
    }

    public static boolean hasMoreHeadlines(){
        if (headlines.size()!=0){
            return true;
        }
        return false;
    }

    public static int size() {
        return headlines.size();
    }
}