package com.wzs.service;

import com.wzs.bean.MicroGuess;
import com.wzs.bean.MicroNotice;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/4/30 13:15
 */
public interface MNoticeService {
    List<MicroNotice> queryMNotice(Map<String, Object> map);
    boolean insertMNotice(MicroNotice notice);
    boolean updateMNotice(MicroNotice notice);
    boolean deleteMNotice(int id);
}
