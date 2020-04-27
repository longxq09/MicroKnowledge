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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<MicroGuess> queryMGuess(Map<String,Object> queryMap){
        return guessService.queryMGuess(queryMap);
    }

    //添加微猜想
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/addMGuess", method = RequestMethod.POST)
    public int insertMGuess(HttpServletRequest request, HttpSession session){
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
    public int updateMGuess(MicroGuess guess){
        guessService.updateMGuess(guess);
        return 0;
    }

    //删除微猜想
    public boolean deleteMGuess(int id){
        return guessService.deleteMGuess(id);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/getMEvid", method = RequestMethod.GET)
    private List<MicroEvidence> getMEvidList(){
        Map<String,Object> queryMap = new HashMap();
        return evidService.queryMEvid(queryMap);
    }

}
