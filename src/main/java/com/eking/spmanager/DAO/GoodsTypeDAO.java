package com.eking.spmanager.dao;

import com.eking.spmanager.domain.GoodsType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-30
 * @Description
 **/

public interface GoodsTypeDAO extends JpaRepository<GoodsType, Integer> {
}
