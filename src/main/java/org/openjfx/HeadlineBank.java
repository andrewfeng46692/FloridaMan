package org.openjfx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HeadlineBank {

    private static ArrayList<Headline> headlines; // ArrayList of all headlines (questions)
    //static

    public HeadlineBank() {
        headlines = new ArrayList<>();
    }

    public void addHeadline(Headline headline) {
        headlines.add(headline);
    }

    public void addHeadlines(ArrayList<Headline> headlines) {
        HeadlineBank.headlines = headlines;
    }

    public void shuffleHeadlines() {
        Collections.shuffle(headlines); // Shuffle for randomness
    }

    public Headline getNextHeadline() {
        return headlines.removeFirst(); // Return and remove the first headline
    }

    public boolean hasMoreHeadlines() {
        return !headlines.isEmpty();
    }

    public int size() {
        return headlines.size();
    }
}