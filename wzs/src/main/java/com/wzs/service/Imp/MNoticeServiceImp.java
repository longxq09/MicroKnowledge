package com.wzs.service.Imp;

import com.wzs.bean.MicroGuess;
import com.wzs.bean.MicroNotice;
import com.wzs.mapper.MNoticeMapper;
import com.wzs.service.MNoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
}
