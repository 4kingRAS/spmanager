package com.eking.spmanager.service;

import com.eking.spmanager.domain.Goods;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-30
 * @Description
 **/

public interface GoodsService {
    void addGoods(Goods g);
    Goods findByName(String name);
    Goods findById(Integer id);
    List<Goods> findByType(String type);
    List<Goods> findByNameIsLike(String name);
    List<Goods> findAllGoods();
    Page<Goods> findGoodsNoCondition(Integer page, Integer size);
    Page<Goods> findGoodsWithCondition(Integer page, Integer size, final Goods goods);
    void update(Goods g);
}
