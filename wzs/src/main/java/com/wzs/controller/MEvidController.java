package com.wzs.controller;

import com.wzs.bean.Account;
import com.wzs.bean.MicroEvidence;
import com.wzs.bean.MicroNotice;
import com.wzs.bean.UserInfo;
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

import static com.wzs.bean.selfEnum.NoticeType.*;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/4/20 12:17
 */
@Controller
@RequestMapping("/mEvidence")
public class MEvidController {

    @Autowired
    private MNoticeService noticeService;

    public List<MicroNotice> queryMEvid(Map<String,Object> queryMap){
        return noticeService.queryMNotice(queryMap);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/addMEvid", method = RequestMethod.POST)
    public int insertMEvid(HttpServletRequest request){
        MicroEvidence evid = new MicroEvidence();
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");

        evid.setType(EVIDENCE.getIndex());
        evid.setAuthorID(userInfo.getId());
        evid.setAuthorName(userInfo.getName());
        evid.setTopic(request.getParameter("topic"));
        evid.setReference(request.getParameter("citedPaper"));
        evid.setKeywords(request.getParameter("keywords"));
        evid.setTitle(request.getParameter("title"));
        evid.setSummary(request.getParameter("summary"));
        evid.setTime(new Date());

        noticeService.insertMNotice(evid);
        return 0;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/modifyMEvid", method = RequestMethod.POST)
    public int updateMEvid(HttpServletRequest request , HttpSession session){
        MicroEvidence evid = new MicroEvidence();
        evid.setId(Integer.parseInt(request.getParameter("id")));

        evid.setTopic(request.getParameter("topic"));
        evid.setReference(request.getParameter("reference"));
        evid.setKeywords(request.getParameter("keywords"));
        evid.setTitle(request.getParameter("title"));
        evid.setSummary(request.getParameter("summary"));
        evid.setTime(new Date());

        noticeService.updateMNotice(evid);
        return 0;
    }

    public boolean deleteMEvid(int id){
        return noticeService.deleteMNotice(id);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/toModifyMEvid", method = RequestMethod.POST)
    private Map<String,Object> toModifyMEvid(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        Map<String,Object> queryMap = new HashMap();
        queryMap.put("id",id);
        List<MicroNotice> list = noticeService.queryMNotice(queryMap);
        MicroNotice evid = list.get(0);

        Map<String,Object> retMap = new HashMap();
        retMap.put("reference",evid.getReference());
        retMap.put("keywords",evid.getKeywords());
        retMap.put("title",evid.getTitle());
        retMap.put("summary",evid.getSummary());
        retMap.put("topic",evid.getTopic());

        return retMap;
    }

}
