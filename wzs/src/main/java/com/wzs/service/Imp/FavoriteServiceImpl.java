package com.wzs.service.Imp;

import com.wzs.bean.Favorite;
import com.wzs.mapper.FavoriteMapper;
import com.wzs.service.FavoriteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Resource
    private FavoriteMapper favoriteMapper;

    @Override
    public List<Favorite> selectFavorite(Map<String, Object> map) {
        return favoriteMapper.selectFavorite(map);
    }

    @Override
    public boolean insertFavorite(Favorite favorite) {
        return favoriteMapper.insertFavorite(favorite);
    }

    @Override
    public boolean deleteFavorite(Favorite favorite) {
        return favoriteMapper.deleteFavorite(favorite);
    }


}
