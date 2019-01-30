package com.eking.spmanager.DAO;

import com.eking.spmanager.entity.GoodsType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-30
 * @Description
 **/

public interface GoodsTypeDAO extends JpaRepository<GoodsType, Integer> {
}
