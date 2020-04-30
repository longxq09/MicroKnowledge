package com.wzs.controller;

import com.wzs.bean.MicroGuess;
import com.wzs.service.MGuessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/4/20 16:41
 */
@Controller
@RequestMapping("/Guess")
public class MGuessController {

    @Autowired
    private MGuessService guessService;

    //通过map查询
    public List<MicroGuess> queryMGuess(Map<String,Object> queryMap){
        return guessService.queryMGuess(queryMap);
    }

    //添加微猜想
    public int insertMGuess(MicroGuess guess, HttpSession session){
//        guess.setAuthorID((Integer) session.getAttribute("authorId"));
        guessService.insertMGuess(guess);
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

    //跳转到添加微猜想界面
    private String toAddMGuess(){
        return "addMGuess";
    }

    //跳转到添加微猜想界面
    private String toUpdateMGuess(){
        return "addMGuess";
    }

}
