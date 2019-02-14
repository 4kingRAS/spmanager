package com.eking.spmanager.dao;

import com.eking.spmanager.domain.GoodsIndex;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-30
 * @Description
 **/

public interface GoodsIdxDAO extends JpaRepository<GoodsIndex, Integer> {
    GoodsIndex findByGoodsid(Integer goodsId);
}
