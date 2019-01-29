package com.eking.spmanager.DAO;

import com.eking.spmanager.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-29
 * @Description
 **/

public interface GoodsDAO extends JpaRepository<Goods, Integer> {
}
