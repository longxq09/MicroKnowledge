package com.wzs.controller;

import com.wzs.bean.MicroEvidence;
import com.wzs.service.MEvidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/4/20 12:17
 */
@Controller
@RequestMapping("/Evidence")
public class MEvidController {

    @Autowired
    private MEvidService mEvidService;

    public List<MicroEvidence> queryMEvid(Map<String,Object> queryMap){
        return mEvidService.queryMEvid(queryMap);
    }

    public int insertMEvid(MicroEvidence mEvid, HttpSession session){
//        mEvid.setAuthorID((Integer) session.getAttribute("authorId"));
        mEvidService.insertMEvid(mEvid);
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
