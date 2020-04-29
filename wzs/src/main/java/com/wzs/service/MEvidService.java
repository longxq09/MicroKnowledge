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
 * @Date 2020/4/20 12:18
 */
public interface MEvidService {

    List<MicroEvidence> queryMEvid(Map<String, Object> map);
    boolean insertMEvid(MicroEvidence evidence);
    int updateMEvid(MicroEvidence evidence);
    boolean deleteMEvid(int id);
    List<MicroEvidence> singleQueryMEvid(String word, String topic);
    List<MicroEvidence> multiQueryMEvid(ArrayList<String> topicList);
    List<MicroEvidence> completeQueryMEvid(SearchLimit searchLimit);

}
