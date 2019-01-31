package com.eking.spmanager.service;

import com.eking.spmanager.entity.Goods;

import java.util.List;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-30
 * @Description
 **/

public interface GoodsService {
    void addGoods(Goods g);
    Goods findByName(String name);
    List<Goods> findByType(String type);
    List<Goods> findAllGoods();
    void update(Goods g);
}
