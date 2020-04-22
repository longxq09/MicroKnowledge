package com.wzs.service.Imp;

import com.wzs.bean.MicroEvidence;
import com.wzs.mapper.MEvidMapper;
import com.wzs.service.MEvidService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/4/20 12:21
 */
@Service
public class MEvidServiceImp implements MEvidService {

    @Resource
    private MEvidMapper evidMapper;

    @Override
    public List<MicroEvidence> queryMEvid(Map<String, Object> map) {
        return evidMapper.selectMEvid(map);
    }

    @Override
    public boolean insertMEvid(MicroEvidence microEvidence) {
        return evidMapper.insertMEvid(microEvidence);
    }

    @Override
    public int updateMEvid(MicroEvidence microEvidence) {
        return evidMapper.updateMEvid(microEvidence);
    }

    @Override
    public boolean deleteMEvid(int id) {
        return evidMapper.deleteMEvidById(id);
    }
}
