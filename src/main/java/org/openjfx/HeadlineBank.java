package org.openjfx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HeadlineBank {

    private List<Headline> headlines; // List of all headlines (questions)

    public HeadlineBank() {
        headlines = new ArrayList<>();
    }

    public void addHeadline(Headline headline) {
        headlines.add(headline);
    }

    public void shuffleHeadlines() {
        Collections.shuffle(headlines); // Shuffle for randomness
    }

    public Headline getNextHeadline() {
        return headlines.remove(0); // Return and remove the first headline
    }

    public boolean hasMoreHeadlines() {
        return !headlines.isEmpty();
    }
}