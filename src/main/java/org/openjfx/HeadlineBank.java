package org.openjfx;
import java.util.ArrayList;
import java.util.Collections;

public class HeadlineBank {
    private static ArrayList<Headline> headlines = new ArrayList<>();


    public static void shuffleHeadlines(){
        Collections.shuffle(headlines);
    }

    public static Headline getNextHeadline(){
        if (headlines.size == 0){
            return null;
        }
        Headline x = headlines.getFirst();
        headlines.removeFirst();
        return x;
    }


    public static ArrayList<Headline> getHeadlines() {
        return headlines;
    }
    public static void setHeadlines(ArrayList<Headline> headlines) {
        HeadlineBank.headlines = headlines;
    }
}
