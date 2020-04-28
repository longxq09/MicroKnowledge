package com.wzs.controller;

import com.wzs.bean.MicroEvidence;
import com.wzs.bean.MicroGuess;
import com.wzs.bean.MicroNotice;
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
        MicroGuess guess = new MicroGuess();
//        mEvid.setAuthorID((Integer) session.getAttribute("authorId"));
        guess.setAuthorID(Integer.parseInt(request.getParameter("authorId")));
        guess.setTopic(request.getParameter("topic"));
        guess.setCitedEvidList(request.getParameter("citedEvidList"));
        guess.setKeywords(request.getParameter("keywords"));
        guess.setTitle(request.getParameter("title"));
        guess.setSummary(request.getParameter("summary"));
        guess.setTime(new Date());
        guessService.insertMGuess(guess);
        return 0;
    }

    //修改微猜想
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/modifyMGuess", method = RequestMethod.POST)
    public int updateMGuess(HttpServletRequest request, HttpSession session) {
        MicroGuess guess = new MicroGuess();
//        mEvid.setAuthorID((Integer) session.getAttribute("authorId"));
        guess.setId(Integer.parseInt(request.getParameter("id")));
        guess.setAuthorID(Integer.parseInt(request.getParameter("authorId")));
        guess.setTopic(request.getParameter("topic"));
        guess.setCitedEvidList(request.getParameter("citedEvidList"));
        guess.setKeywords(request.getParameter("keywords"));
        guess.setTitle(request.getParameter("title"));
        guess.setSummary(request.getParameter("summary"));
        guess.setTime(new Date());
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
    @ResponseBody
    @RequestMapping(value = "/toModifyMGuess", method = RequestMethod.POST)
    private Map<String, Object> toModifyMGuess(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Map<String, Object> queryMap = new HashMap();
        queryMap.put("id", id);
        List<MicroGuess> list = guessService.queryMGuess(queryMap);
        MicroGuess guess = list.get(0);
        Map<String, Object> retMap = new HashMap();
        retMap.put("topic", guess.getTopic());
        retMap.put("citedEvidList", guess.getCitedEvidList());
        retMap.put("keywords", guess.getKeywords());
        retMap.put("title", guess.getTitle());
        retMap.put("summary", guess.getSummary());
        return retMap;
    }

    @CrossOrigin
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public @ResponseBody
    Object getSearch(HttpServletRequest request) {
        String word = request.getParameter("word");     //关键词
        int kind = Integer.parseInt(request.getParameter("kind"));  // Evidence-Guess: 00,01,10,11
        String topic = request.getParameter("topic");     //id-id-id
        //ArrayList<String> topicList = new ArrayList<>();
        String topics = "%[";
        String[] tmp = topic.split("-");
        for (int i = 0; i < tmp.length; i++) {
            //System.out.println(tmp[i]);
            topics = topics.concat(tmp[i]);
        }
        topics = topics.concat("]%");
        List<MicroGuess> guessList;
        List<MicroEvidence> evidenceList;
        HashMap<String, Object> res = new HashMap<>();
        if (kind == 1) {
            guessList = guessService.fuzzyQueryMGuess(word, topics);
            res.put("kind", "1");
            res.put("guess", guessList);
        } else if (kind == 2) {
            evidenceList = evidService.fuzzyQueryMEvid(word, topics);
            res.put("kind", "2");
            res.put("evidence", evidenceList);
        } else {
            guessList = guessService.fuzzyQueryMGuess(word, topics);
            evidenceList = evidService.fuzzyQueryMEvid(word, topics);
            res.put("kind", "0");
            res.put("guess", guessList);
            res.put("evidence", evidenceList);
        }
        return res;
    }
}
