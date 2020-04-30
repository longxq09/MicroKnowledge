package com.wzs.controller;

import com.wzs.bean.MicroEvidence;
import com.wzs.bean.MicroGuess;
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
 * @Date 2020/4/20 16:44
 */
@SpringBootTest
class MGuessControllerTest {

    @Autowired
    private MGuessController controller;

    @Test
    public void testInsert(){
        MicroGuess guess = new MicroGuess();
        guess.setAuthorID(1);
        guess.setCitedEvidList("evid-evid-evid");
        guess.setTopic("计算机科学与技术");
        guess.setKeywords("A,B,C,D");
        guess.setSummary("summary");
        try {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date time = sdf.parse("2020/04/21 02:58:00");
            guess.setTime(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        controller.insertMGuess(guess,null);
        MicroGuess guess2 = new MicroGuess();
        guess2.setAuthorID(2);
        guess2.setCitedEvidList("evid-evid-evid");
        guess2.setTopic("计算机科学与技术");
        guess2.setKeywords("A,B,C,D");
        guess2.setSummary("summary");
        try {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date time = sdf.parse("2020/04/21 02:58:00");
            guess2.setTime(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        controller.insertMGuess(guess,null);
    }

    @Test
    public void testUpdate(){
        MicroGuess guess = new MicroGuess();
        guess.setId(1);
        guess.setCitedEvidList("update");
        guess.setTopic("update");
        guess.setKeywords("update");
        guess.setSummary("update");
        controller.updateMGuess(guess);
    }

    @Test
    public void testSelect(){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("id",1);
        List<MicroGuess> list =  controller.queryMGuess(queryMap);
        for(MicroGuess o : list){
            System.out.println(o.getId());
        }

        queryMap.remove("id");
        queryMap.put("topic","update");
        list =  controller.queryMGuess(queryMap);
        for(MicroGuess o : list){
            System.out.println(o.getId());
        }

        queryMap.remove("topic");
        queryMap.put("authorId",1);
        list =  controller.queryMGuess(queryMap);
        for(MicroGuess o : list){
            System.out.println(o.getId());
        }

    }

    @Test
    public void testDelete(){
        controller.deleteMGuess(2);
    }

}