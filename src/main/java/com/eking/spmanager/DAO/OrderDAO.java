package com.eking.spmanager.DAO;

import com.eking.spmanager.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Yulin.Wang
 * @Date 2019-02-10
 * @Description
 **/

public interface OrderDAO extends JpaRepository<Orders, Integer> {
}
