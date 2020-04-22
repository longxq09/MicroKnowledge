package com.wzs.controller;

import com.wzs.bean.MicroEvidence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/4/20 14:22
 */
@SpringBootTest
class MEvidControllerTest {

    @Autowired
    private MEvidController controller;

    @Test
    public void testInsert(){
        MicroEvidence evid = new MicroEvidence();
        evid.setAuthorID(1);
        evid.setCitedPaper("citation");
        evid.setTopic("计算机科学与技术");
        evid.setKeywords("A,B,C,D");
        evid.setSummary("summary");
        try {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date time = sdf.parse("2020/04/21 02:58:00");
            evid.setTime(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        controller.insertMEvid(evid,null);
    }

    @Test
    public void testUpdate(){
        MicroEvidence evid = new MicroEvidence();
        evid.setId(1);
        evid.setCitedPaper("update");
        evid.setTopic("update");
        evid.setKeywords("update");
        evid.setSummary("update");
        controller.updateMEvid(evid);
    }

    @Test
    public void testSelect(){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("id",1);
        List<MicroEvidence> list =  controller.queryMEvid(queryMap);
        for(MicroEvidence o : list){
            System.out.println(o.getId());
        }

        queryMap.remove("id");
        queryMap.put("topic","update");
        list =  controller.queryMEvid(queryMap);
        for(MicroEvidence o : list){
            System.out.println(o.getId());
        }

        queryMap.remove("topic");
        queryMap.put("authorId",1);
        list =  controller.queryMEvid(queryMap);
        for(MicroEvidence o : list){
            System.out.println(o.getId());
        }

    }

    @Test
    public void testDelete(){
        controller.deleteMEvid(2);
    }

}