package com.wzs.controller;

import com.wzs.bean.MicroEvidence;
import com.wzs.bean.MicroGuess;
import com.wzs.bean.SearchLimit;
import com.wzs.service.MEvidService;
import com.wzs.service.MGuessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class SearchController {
    @Autowired
    private MGuessService guessService;
    @Autowired
    private MEvidService evidService;

    //关键字+多标签
    List<MicroEvidence> getCompleteSearchMevid(String word, ArrayList<String> topicList) {
        SearchLimit searchLimit = new SearchLimit();
        searchLimit.setTopicList(topicList);
        searchLimit.setWord(word);
        return evidService.completeQueryMEvid(searchLimit);
    }

    List<MicroGuess> getCompleteSearchMGuess(String word, ArrayList<String> topicList) {
        SearchLimit searchLimit = new SearchLimit();
        searchLimit.setTopicList(topicList);
        searchLimit.setWord(word);
        return guessService.completeQueryMGuess(searchLimit);
    }

    @CrossOrigin
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public @ResponseBody
    Object getSearch(HttpServletRequest request) {
        String word = request.getParameter("word");     //关键词
        System.out.println(word);
        int kind = Integer.parseInt(request.getParameter("kind"));  // Evidence-Guess: 00,01,10,11
        String topic = request.getParameter("topic");     //id-id-id
        ArrayList<String> topicList = new ArrayList<>();
        if (topic==null) {
            topicList.add("%");         //标签为空
        } else {
            String[] tmp = topic.split("-");
            for (int i = 0; i < tmp.length; i++) {
                topicList.add("%-" + tmp[i] + "-%");
            }
        }
        word = "%" + word + "%";
        List<MicroGuess> guessList;
        List<MicroEvidence> evidenceList;
        HashMap<String, Object> res = new HashMap<>();
        if (kind == 1) {
            guessList = getCompleteSearchMGuess(word, topicList);
            res.put("guess", guessList);
        } else if (kind == 2) {
            evidenceList = getCompleteSearchMevid(word, topicList);
            res.put("evidence", evidenceList);
        } else {
            guessList = getCompleteSearchMGuess(word, topicList);
            evidenceList = getCompleteSearchMevid(word, topicList);
            res.put("guess", guessList);
            res.put("evidence", evidenceList);
        }
        res.put("kind", request.getParameter("kind"));
        return res;
    }

    //关键字+单一标签
    List<MicroEvidence> getSingleSearchMevid(String word, String topic) {
        return evidService.singleQueryMEvid(word, topic);
    }

    List<MicroGuess> getSingleSearchMGuess(String word, String topic) {
        return guessService.singleQueryMGuess(word, topic);
    }

    //多个标签
    List<MicroEvidence> getMultiSearchMevid(ArrayList<String> topicList) {
        return evidService.multiQueryMEvid(topicList);
    }

    List<MicroGuess> getMultiSearchMGuess(ArrayList<String> topicList) {
        return guessService.multiQueryMGuess(topicList);
    }

}
