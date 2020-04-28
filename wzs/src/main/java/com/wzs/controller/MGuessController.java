package com.wzs.controller;

import com.wzs.bean.MicroEvidence;
import com.wzs.bean.MicroGuess;
import com.wzs.service.MEvidService;
import com.wzs.service.MGuessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/4/20 16:41
 */
@Controller
@RequestMapping("/MGuess")
public class MGuessController {

    @Autowired
    private MGuessService guessService;
    @Autowired
    private MEvidService evidService;

    //通过map查询
    public List<MicroGuess> queryMGuess(Map<String, Object> queryMap) {
        return guessService.queryMGuess(queryMap);
    }

    //添加微猜想
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/addMGuess", method = RequestMethod.POST)
    public int insertMGuess(HttpServletRequest request, HttpSession session) {
        MicroGuess evid = new MicroGuess();
//        mEvid.setAuthorID((Integer) session.getAttribute("authorId"));
        evid.setAuthorID(Integer.parseInt(request.getParameter("authorId")));
        evid.setTopic(request.getParameter("topic"));
        evid.setCitedEvidList(request.getParameter("citedEvidList"));
        evid.setKeywords(request.getParameter("keywords"));
        evid.setTitle(request.getParameter("title"));
        evid.setSummary(request.getParameter("summary"));
        evid.setTime(new Date());
        guessService.insertMGuess(evid);
        return 0;
    }

    //修改微猜想
    public int updateMGuess(MicroGuess guess) {
        guessService.updateMGuess(guess);
        return 0;
    }

    //删除微猜想
    public boolean deleteMGuess(int id) {
        return guessService.deleteMGuess(id);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/getMEvid", method = RequestMethod.GET)
    private List<MicroEvidence> getMEvidList() {
        Map<String, Object> queryMap = new HashMap();
        return evidService.queryMEvid(queryMap);
    }

    @CrossOrigin
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public @ResponseBody
    Object getSearch(HttpServletRequest request) {
        String word = request.getParameter("word");     //关键词
        int kind = Integer.parseInt(request.getParameter("kind"));  // Evidence-Guess: 00,01,10,11
        String topic = request.getParameter("topic");     //id-id-id
        ArrayList<String> topicList = new ArrayList<>();
        String[] tmp = topic.split("-");
        for (int i = 0; i < tmp.length; i++) {
            //System.out.println(tmp[i]);
            topicList.add(tmp[i]);
        }
        List<MicroGuess> guessList;
        List<MicroEvidence> evidenceList;
        HashMap<String, Object> res = new HashMap<>();
        if (kind == 1) {
            guessList = guessService.fuzzyQueryMGuess(word, topicList);
            res.put("kind", "1");
            res.put("guess", guessList);
        } else if (kind == 2) {
            evidenceList = evidService.fuzzyQueryMEvid(word, topicList);
            res.put("kind", "2");
            res.put("evidence", evidenceList);
        } else {
            guessList = guessService.fuzzyQueryMGuess(word, topicList);
            evidenceList = evidService.fuzzyQueryMEvid(word, topicList);
            res.put("kind", "0");
            res.put("guess", guessList);
            res.put("evidence", evidenceList);
        }

        return res;
    }

}
