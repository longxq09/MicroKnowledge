package com.wzs.service;

import com.wzs.bean.MicroEvidence;
import com.wzs.bean.MicroGuess;
import com.wzs.bean.SearchLimit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/4/20 16:34
 */
public interface MGuessService {

    List<MicroGuess> queryMGuess(Map<String, Object> map);
    boolean insertMGuess(MicroGuess guess);
    int updateMGuess(MicroGuess guess);
    boolean deleteMGuess(int id);
    List<MicroGuess> singleQueryMGuess(String word, String topics);
    List<MicroGuess> multiQueryMGuess(ArrayList<String> topicList);
    List<MicroGuess> completeQueryMGuess(SearchLimit searchLimit);
}
