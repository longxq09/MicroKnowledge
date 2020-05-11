package com.wzs.mapper;

import com.wzs.bean.Favorite;
import com.wzs.bean.MicroNotice;
import com.wzs.bean.SearchLimit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
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

    MicroNotice getMNoticeById(int id);

    boolean insertMNotice(MicroNotice Notice);

    boolean updateMNotice(MicroNotice Notice);

    boolean deleteMNoticeById(int id);

    List<MicroNotice> multiQueryMNotice(ArrayList<String> topicList);

    List<MicroNotice> completeQueryMNotice(@Param("search") SearchLimit search);

    List<MicroNotice> selectMNoticeByAuthorList(ArrayList<Integer> authorList);

    List<MicroNotice> selectMNoticeByFavorite(List<Favorite> favorites);
}
