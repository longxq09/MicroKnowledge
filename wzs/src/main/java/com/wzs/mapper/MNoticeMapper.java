package com.wzs.mapper;

import com.wzs.bean.MicroGuess;
import com.wzs.bean.MicroNotice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/4/30 13:06
 */
@Mapper
public interface MNoticeMapper {
    List<MicroNotice> selectMNotice(Map<String, Object> map);
    boolean insertMNotice(MicroNotice Notice);
    boolean updateMNotice(MicroNotice Notice);
    boolean deleteMNoticeById(int id);
}
