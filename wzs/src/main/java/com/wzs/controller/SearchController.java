package com.wzs.controller;

import com.wzs.bean.MicroNotice;
import com.wzs.bean.SearchLimit;
import com.wzs.service.MNoticeService;
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
    private MNoticeService mNoticeService;

    //关键字+多标签
    List<MicroNotice> getCompleteSearchMNotice(String word, ArrayList<String> topicList, ArrayList<Integer> typeList) {
        SearchLimit searchLimit = new SearchLimit();
        searchLimit.setTopicList(topicList);
        searchLimit.setWord(word);
        searchLimit.setTypeList(typeList);
        return mNoticeService.completeQueryMNotice(searchLimit);
    }


    @CrossOrigin
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public @ResponseBody
    Object getSearch(HttpServletRequest request) {
        String word = request.getParameter("word");     //关键词
        //System.out.println(word);
        int kind = Integer.parseInt(request.getParameter("kind"));  // Evidence-Guess: 00,01,10,11
        String topic = request.getParameter("topic");     //id-id-id
        ArrayList<String> topicList = new ArrayList<>();
        if (topic.equals("")) {
            topicList.add("%");         //标签为空
        } else {
            String[] tmp = topic.split("-");
            for (int i = 0; i < tmp.length; i++) {
                topicList.add("%-" + tmp[i] + "-%");
            }
        }
        ArrayList<Integer> typeList = new ArrayList<>();
        if (kind == 1) {
            typeList.add(1);
        } else if (kind == 2) {
            typeList.add(2);
        } else {
            typeList.add(1);
            typeList.add(2);
        }
        word = "%" + word + "%";
        List<MicroNotice> noticeList = getCompleteSearchMNotice(word, topicList, typeList);
        HashMap<String, Object> res = new HashMap<>();
        res.put("notice", noticeList);
        res.put("kind", request.getParameter("kind"));
        return res;
    }


    //多个标签
    List<MicroNotice> getMultiSearch(ArrayList<String> topicList) {
        return mNoticeService.multiQueryMNotice(topicList);
    }


}
