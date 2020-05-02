package com.wzs.controller;

import com.wzs.bean.MicroEvidence;
import com.wzs.bean.MicroGuess;
import com.wzs.bean.MicroNotice;
import com.wzs.service.MEvidService;
import com.wzs.service.MGuessService;
import com.wzs.service.MNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

import static com.wzs.bean.Type.*;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/4/20 16:41
 */
@Controller
@RequestMapping("/mGuess")
public class MGuessController {

    @Autowired
    private MNoticeService noticeService;

    //通过map查询
    public List<MicroNotice> queryMGuess(Map<String, Object> queryMap) {
        return noticeService.queryMNotice(queryMap);
    }

    //添加微猜想
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/addMGuess", method = RequestMethod.POST)
    public int insertMGuess(HttpServletRequest request, HttpSession session) {
        MicroGuess guess = new MicroGuess();
        guess.setType(GUESS.getIndex());
//        mEvid.setAuthorID((Integer) session.getAttribute("authorId"));
        guess.setAuthorID(Integer.parseInt(request.getParameter("authorId")));
        guess.setAuthorName(request.getParameter("authorName"));
        guess.setTopic(request.getParameter("topic"));
        guess.setReference(request.getParameter("reference"));
        guess.setKeywords(request.getParameter("keywords"));
        guess.setTitle(request.getParameter("title"));
        guess.setSummary(request.getParameter("summary"));
        guess.setTime(new Date());

        noticeService.insertMNotice(guess);
        return 0;
    }

    //修改微猜想
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/modifyMGuess", method = RequestMethod.POST)
    public int updateMGuess(HttpServletRequest request, HttpSession session) {
        MicroGuess guess = new MicroGuess();
        guess.setId(Integer.parseInt(request.getParameter("id")));

        guess.setAuthorName(request.getParameter("authorName"));
        guess.setTopic(request.getParameter("topic"));
        guess.setReference(request.getParameter("reference"));
        guess.setKeywords(request.getParameter("keywords"));
        guess.setTitle(request.getParameter("title"));
        guess.setSummary(request.getParameter("summary"));
        guess.setTime(new Date());

        noticeService.updateMNotice(guess);
        return 0;
    }

    //删除微猜想
    public boolean deleteMGuess(int id) {
        return noticeService.deleteMNotice(id);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/getMEvid", method = RequestMethod.GET)
    private List<MicroNotice> getMEvidList() {
        Map<String, Object> queryMap = new HashMap();
        queryMap.put("type",EVIDENCE.getIndex());
        return noticeService.queryMNotice(queryMap);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/toModifyMGuess", method = RequestMethod.POST)
    private Map<String, Object> toModifyMGuess(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Map<String, Object> queryMap = new HashMap();
        queryMap.put("id", id);
        List<MicroNotice> list = noticeService.queryMNotice(queryMap);
        MicroNotice guess = list.get(0);

        Map<String, Object> retMap = new HashMap();
        retMap.put("topic", guess.getTopic());
        retMap.put("reference", guess.getReference());
        retMap.put("keywords", guess.getKeywords());
        retMap.put("title", guess.getTitle());
        retMap.put("summary", guess.getSummary());

        return retMap;
    }


}
