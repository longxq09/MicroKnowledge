package com.wzs.bean;

import java.util.List;

public class SearchLimit {
    List<String> topicList;
    String word;

    public List<String> getTopicList() {
        return topicList;
    }

    public String getWord() {
        return word;
    }

    public void setTopicList(List<String> topicList) {
        this.topicList = topicList;
    }

    public void setWord(String word) {
        this.word = word;
    }
}

