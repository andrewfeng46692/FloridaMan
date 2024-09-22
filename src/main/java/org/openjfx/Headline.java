
package org.openjfx;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;


public class Headline {
    // Fields for the story, the keyword, and the options
    private String story;
    private String keyword;
    private String[] options;



    // Constructor
    public Headline(String story, String keyword, String[] options) {
        this.story = story;
        this.keyword = keyword;
        if (options != null && options.length == 3) {
            this.options = options;
        } else {
            this.options = new String[3];
        }
        HeadlineBank.addHeadline(this); // Automatically add to HeadlineBank
    }


    // Getters and Setters
    public String getStory() {
        return story;
    }
    public String getKeyword() {
        return keyword;
    }
    public String[] getOptions() {
        return options;
    }
    public void setStoryContent(String storyContent) {
        this.story = storyContent;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    public void setOptions(String[] options) {
        if (options != null && options.length == 3) {
            this.options = options;
        }
    }


    // Replace the keyword in the story with blank
    public String getStoryWithBlanks() {
        return story.replace(keyword, "_____");
    }


    // A method to validate the user’s guess
    public boolean isCorrectGuess(String guess) {
        return this.keyword.equalsIgnoreCase(guess);
    }
}
