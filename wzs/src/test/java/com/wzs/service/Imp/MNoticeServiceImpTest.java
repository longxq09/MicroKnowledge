package com.wzs.service.Imp;

import com.wzs.bean.MicroNotice;
import com.wzs.service.MNoticeService;
import org.apache.commons.collections.map.HashedMap;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/4/30 13:26
 */
@SpringBootTest
class MNoticeServiceImpTest {

    @Resource
    private MNoticeService noticeService;

    @Test
    void insertMNotice() {
        MicroNotice notice = new MicroNotice();
        notice.setType(1);
        notice.setAuthorID(1);
        notice.setAuthorName("wzs");
        notice.setTopic("1-2-3");
        notice.setReference("1-2-3");
        notice.setKeywords("a-b-c");
        notice.setTitle("title");
        notice.setSummary("summary");
        notice.setTime(new Date());
        noticeService.insertMNotice(notice);
        noticeService.insertMNotice(notice);
    }

    @Test
    void queryMNotice() {
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("id",1);
        noticeService.queryMNotice(queryMap);
    }

    @Test
    void updateMNotice() {
        MicroNotice notice = new MicroNotice();
        notice.setId(2);
        notice.setAuthorName("wzs");
        notice.setTopic("3-2-1");
        notice.setReference("3-2-1");
        notice.setKeywords("aa-bb-cc");
        notice.setTitle("title2");
        notice.setSummary("summary2");
        notice.setTime(new Date());
        noticeService.updateMNotice(notice);
    }

    @Test
    void deleteMNotice() {
        noticeService.deleteMNotice(2);
    }
}