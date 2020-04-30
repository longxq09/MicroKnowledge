package com.wzs.bean;

import java.util.List;

public class SearchLimit {
    private List<String> topicList;
    private String word;
    private List<Integer> typeList;

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

    public List<Integer> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<Integer> typeList) {
        this.typeList = typeList;
    }
}

