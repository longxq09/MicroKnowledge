package com.wzs.service.Imp;

import com.wzs.bean.Favorite;
import com.wzs.bean.MicroGuess;
import com.wzs.bean.MicroNotice;
import com.wzs.bean.SearchLimit;
import com.wzs.mapper.MNoticeMapper;
import com.wzs.service.MNoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/4/30 13:15
 */
@Service
public class MNoticeServiceImp implements MNoticeService {
    @Resource
    private MNoticeMapper noticeMapper;

    @Override
    public List<MicroNotice> queryMNotice(Map<String, Object> map) {
        return noticeMapper.selectMNotice(map);
    }

    @Override
    public MicroNotice getMNoticeById(int id) {
        return noticeMapper.getMNoticeById(id);
    }

    @Override
    public boolean insertMNotice(MicroNotice notice) {
        return noticeMapper.insertMNotice(notice);
    }

    @Override
    public boolean updateMNotice(MicroNotice notice) {
        return noticeMapper.updateMNotice(notice);
    }

    @Override
    public boolean deleteMNotice(int id) {
        return noticeMapper.deleteMNoticeById(id);
    }

    @Override
    public List<MicroNotice> multiQueryMNotice(ArrayList<String> topicList){
        return  noticeMapper.multiQueryMNotice(topicList);
    }

    @Override
    public List<MicroNotice> completeQueryMNotice(SearchLimit searchLimit){
        return noticeMapper.completeQueryMNotice(searchLimit);
    }

    @Override
    public List<MicroNotice> selectMNoticeByAuthorList(ArrayList<Integer> authorList){
        return noticeMapper.selectMNoticeByAuthorList(authorList);
    }

    @Override
    public List<MicroNotice> selectMNoticeByFavorite(List<Favorite> favorites){
        return noticeMapper.selectMNoticeByFavorite(favorites);
    }
}
