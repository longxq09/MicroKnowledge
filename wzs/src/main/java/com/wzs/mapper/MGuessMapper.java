package com.wzs.mapper;

import com.wzs.bean.MicroEvidence;
import com.wzs.bean.MicroGuess;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/4/20 16:36
 */
@Mapper
public interface MGuessMapper {
    List<MicroGuess> selectMGuess(Map<String, Object> map);
    boolean insertMGuess(MicroGuess guess);
    int updateMGuess(MicroGuess guess);
    boolean deleteMGuessById(int id);
}