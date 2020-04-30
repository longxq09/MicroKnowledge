package com.wzs.controller;

import com.wzs.bean.MicroEvidence;
import com.wzs.bean.MicroGuess;
import com.wzs.bean.SearchLimit;
import com.wzs.service.MEvidService;
import com.wzs.service.MGuessService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class SearchControllerTest {

    @Autowired
    private SearchController searchController;
    @Autowired
    private MGuessService guessService;
    @Autowired
    private MEvidService evidService;

    /*@Test
    public void testController(){
        String word = null;     //关键词 "1"
        word = "%" + word + "%";
        String topic = "1-2-14";     //id-id-id
        ArrayList<String> topicList = new ArrayList<>();
        String[] tmp = topic.split("-");
        for (int i = 0; i < tmp.length; i++) {
            topicList.add("%-" + tmp[i] + "-%");
        }
        System.out.println(topicList);
        List<MicroEvidence> evidenceList;
        List<MicroGuess> guessList;
        //evidenceList=searchController.getMultiSearchMevid(topicList);
        //guessList=searchController.getMultiSearchMGuess(topicList);
        evidenceList=searchController.getSingleSearchMevid(word,topicList.get(0));
        guessList=searchController.getSingleSearchMGuess(word,topicList.get(0));
        for (int i = 0; i < evidenceList.size(); i++)
            System.out.println(evidenceList.get(i).getTopic());
        System.out.println("=======================");
        for (int i = 0; i < guessList.size(); i++)
            System.out.println(guessList.get(i).getTopic());
    }

    @Test
    public void testMultSearch() {
        String word = "%%";     //关键词
        String topic = "";     //id-id-id
        ArrayList<String> topicList = new ArrayList<>();
        if(topic.equals("")){
            topicList.add("%");
        }else{
            String[] tmp = topic.split("-");
            for (int i = 0; i < tmp.length; i++) {
                topicList.add("%-" + tmp[i] + "-%");
            }
        }
        System.out.println(topicList);

        SearchLimit searchLimit = new SearchLimit();
        searchLimit.setTopicList(topicList);
        searchLimit.setWord(word);
        List<MicroEvidence> evidenceList = evidService.completeQueryMEvid(searchLimit);
        List<MicroGuess> guessList = guessService.completeQueryMGuess(searchLimit);
        for (int i = 0; i < evidenceList.size(); i++)
            System.out.println(evidenceList.get(i).getTopic());
        System.out.println("=======================");
        for (int i = 0; i < guessList.size(); i++)
            System.out.println(guessList.get(i).getTopic());
    }*/

}