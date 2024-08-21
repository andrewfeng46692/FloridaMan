package org.openjfx;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class Headline {
    private String storyContent;
    private String keyword;
    private ArrayList<String> options;

    public Headline(String storyContent, String keyword, ArrayList<String> otherOptions) {
        this.storyContent = storyContent;
        this.keyword = keyword;
        this.options = new ArrayList<>(otherOptions);
        this.options.add(keyword);
        Collections.shuffle(this.options);
    }

    public String getStoryContent() {
        return storyContent;
    }

    public String getKeyword() {
        return keyword;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setStoryContent(String storyContent) {
        this.storyContent = storyContent;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setOtherOptions(ArrayList<String> options) {
        this.options = options;
    }
    public String removedHeadline(String storyContent, String keyword) {
        return storyContent.replace(keyword, "________");
    }
}
