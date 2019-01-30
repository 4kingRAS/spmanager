package com.eking.spmanager.DAO;

import com.eking.spmanager.entity.GoodsIndex;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-30
 * @Description
 **/

public interface GoodsIdxDAO extends JpaRepository<GoodsIndex, Integer> {
}
