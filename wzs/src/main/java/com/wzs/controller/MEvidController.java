package com.wzs.controller;

import com.wzs.bean.MicroEvidence;
import com.wzs.service.MEvidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    public List<MicroEvidence> queryMEvid(Map<String,Object> queryMap){
        return mEvidService.queryMEvid(queryMap);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/addMEvid", method = RequestMethod.POST)
    public int insertMEvid(HttpServletRequest request , HttpSession session){
        MicroEvidence evid = new MicroEvidence();
//        mEvid.setAuthorID((Integer) session.getAttribute("authorId"));
        evid.setAuthorID(Integer.parseInt(request.getParameter("authorId")));
        evid.setTopic(request.getParameter("topic"));
        evid.setCitedPaper(request.getParameter("citedPaper"));
        evid.setKeywords(request.getParameter("keywords"));
        evid.setTitle(request.getParameter("title"));
        evid.setSummary(request.getParameter("summary"));
        evid.setTime(new Date());
        mEvidService.insertMEvid(evid);

        return 0;
    }

    public int updateMEvid(MicroEvidence mEvid){
        mEvidService.updateMEvid(mEvid);
        return 0;
    }

    public boolean deleteMEvid(int id){
        return mEvidService.deleteMEvid(id);
    }

    private String toAddMEvdi(){
        return "addMEvid";
    }

    private String toUpdateMEvdi(){
        return "addMEvid";
    }

}
