package com.wzs.controller;

import com.wzs.bean.MicroGuess;
import com.wzs.bean.MicroNotice;
import com.wzs.service.MNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/4/30 13:37
 */
@Controller
@RequestMapping("/mNotice")
public class MNoticeController {
    @Autowired
    private MNoticeService noticeService;

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/getNotices", method = RequestMethod.GET)
    public List<MicroNotice> queryMNotice(Map<String, Object> queryMap) {
        return noticeService.queryMNotice(queryMap);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/deleteNotice", method = RequestMethod.POST)
    public int deleteMEvid(int id){
        noticeService.deleteMNotice(id);
        return 0;
    }

}
