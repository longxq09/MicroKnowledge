package com.wzs.mapper;

import com.wzs.bean.MicroEvidence;
import org.apache.ibatis.annotations.Mapper;

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
    List<MicroEvidence> fuzzyQueryMEvid(String word, String topics);
}
