package com.eking.spmanager.service;

import com.eking.spmanager.entity.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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
    List<Goods> findAllGoods();
    Page<Goods> findGoodsNoCondition(Integer page, Integer size);
    Page<Goods> findGoodsWithCondition(Integer page, Integer size, final Goods goods);
    void update(Goods g);
}
