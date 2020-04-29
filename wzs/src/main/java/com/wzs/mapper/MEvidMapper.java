package com.wzs.mapper;

import com.wzs.bean.MicroEvidence;
import com.wzs.bean.SearchLimit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/4/20 12:27
 */
@Mapper
public interface MEvidMapper {
    List<MicroEvidence> selectMEvid(Map<String, Object> map);
    boolean insertMEvid(MicroEvidence evid);
    int updateMEvid(MicroEvidence evid);
    boolean deleteMEvidById(int id);
    List<MicroEvidence> singleQueryMEvid(String word, String topic);       //关键字+单一标签
    List<MicroEvidence> multiQueryMEvid(ArrayList<String> topicList);        //多个标签
    List<MicroEvidence> completeQueryMEvid(@Param("search")SearchLimit search); //关键字+多标签
}
