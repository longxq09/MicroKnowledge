package com.wzs.controller;

import com.wzs.bean.*;
import com.wzs.service.TopicService;
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
    private TopicService topicService;


    @Test
    public void testTopic(){
        Map<String,Object> queryMap = new HashMap();
        List<Topic> topicList=topicService.queryTopic(queryMap);
        for (int i = 0; i < topicList.size(); i++)
            System.out.println(topicList.get(i).getTopicName());
    }

    @Test
    public void testMultiSearch() {
        String word = "%wzs%";     //关键词
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
        int kind =0;
        ArrayList<Integer> typeList = new ArrayList<>();
        if (kind == 1) {
            typeList.add(1);
        } else if (kind == 2) {
            typeList.add(2);
        } else {
            typeList.add(1);
            typeList.add(2);
        }
        List<MicroNotice> noticeList = searchController.getCompleteSearchMNotice(word,topicList,typeList);

        for (int i = 0; i < noticeList.size(); i++)
            System.out.println(noticeList.get(i).getTopic());

    }

}
