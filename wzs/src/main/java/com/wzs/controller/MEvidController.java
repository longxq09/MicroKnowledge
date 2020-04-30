package com.wzs.controller;

import com.wzs.bean.MicroEvidence;
import com.wzs.service.MEvidService;
import com.wzs.service.TopicService;
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
 * @Date 2020/4/20 12:17
 */
@Controller
@RequestMapping("/MEvidence")
public class MEvidController {

    @Autowired
    private MEvidService mEvidService;
    @Autowired
    private TopicService topicService;

    public List<MicroEvidence> queryMEvid(Map<String,Object> queryMap){
        return mEvidService.queryMEvid(queryMap);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/addMEvid", method = RequestMethod.POST)
    public int insertMEvid(HttpServletRequest request , HttpSession session){
        MicroEvidence evid = new MicroEvidence();
//        mEvid.setAuthorID((Integer) session.getAttribute("authorId"));
        evid.setType(1);
        evid.setAuthorID(Integer.parseInt(request.getParameter("authorId")));
        evid.setTopic(request.getParameter("topic"));
        evid.setReference(request.getParameter("citedPaper"));
        evid.setKeywords(request.getParameter("keywords"));
        evid.setTitle(request.getParameter("title"));
        evid.setSummary(request.getParameter("summary"));
        evid.setTime(new Date());
        mEvidService.insertMEvid(evid);

        return 0;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/modifyMEvid", method = RequestMethod.POST)
    public int updateMEvid(HttpServletRequest request , HttpSession session){
        MicroEvidence evid = new MicroEvidence();
//        mEvid.setAuthorID((Integer) session.getAttribute("authorId"));
        evid.setId(Integer.parseInt(request.getParameter("id")));
        evid.setType(1);
        evid.setAuthorID(Integer.parseInt(request.getParameter("authorId")));
        evid.setTopic(request.getParameter("topic"));
        evid.setReference(request.getParameter("citedPaper"));
        evid.setKeywords(request.getParameter("keywords"));
        evid.setTitle(request.getParameter("title"));
        evid.setSummary(request.getParameter("summary"));
        evid.setTime(new Date());
        mEvidService.updateMEvid(evid);
        return 0;
    }

    public boolean deleteMEvid(int id){
        return mEvidService.deleteMEvid(id);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/toModifyMEvid", method = RequestMethod.POST)
    private Map<String,Object> toModifyMEvid(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        Map<String,Object> queryMap = new HashMap();
        queryMap.put("id",id);
        List<MicroEvidence> list = mEvidService.queryMEvid(queryMap);
        MicroEvidence evid = list.get(0);
        Map<String,Object> retMap = new HashMap();
        retMap.put("citedPaper",evid.getReference());
        retMap.put("keywords",evid.getKeywords());
        retMap.put("title",evid.getTitle());
        retMap.put("summary",evid.getSummary());

        retMap.put("topic",evid.getTopic());
        return retMap;
    }

}
