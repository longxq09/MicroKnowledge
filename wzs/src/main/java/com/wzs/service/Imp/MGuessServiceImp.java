package com.wzs.service.Imp;

import com.wzs.bean.MicroEvidence;
import com.wzs.bean.MicroGuess;
import com.wzs.bean.SearchLimit;
import com.wzs.mapper.MEvidMapper;
import com.wzs.mapper.MGuessMapper;
import com.wzs.service.MGuessService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/4/20 16:34
 */
@Service
public class MGuessServiceImp implements MGuessService {

    @Resource
    private MGuessMapper guessMapper;

    @Override
    public List<MicroGuess> queryMGuess(Map<String, Object> map) {
        return guessMapper.selectMGuess(map);
    }

    @Override
    public boolean insertMGuess(MicroGuess guess) {
        return guessMapper.insertMGuess(guess);
    }

    @Override
    public int updateMGuess(MicroGuess guess) {
        return guessMapper.updateMGuess(guess);
    }

    @Override
    public boolean deleteMGuess(int id) {
        return guessMapper.deleteMGuessById(id);
    }

    @Override
    public List<MicroGuess> singleQueryMGuess(String word, String topics) {
        return guessMapper.singleQueryMGuess(word, topics);
    }

    @Override
    public List<MicroGuess> multiQueryMGuess(ArrayList<String> topicList) {
        return guessMapper.multiQueryMGuess(topicList);
    }

    @Override
    public List<MicroGuess> completeQueryMGuess(SearchLimit searchLimit) {
        return guessMapper.completeQueryMGuess(searchLimit);
    }
}
