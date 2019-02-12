package com.eking.spmanager.DAO;

import com.eking.spmanager.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Yulin.Wang
 * @Date 2019-02-10
 * @Description
 **/

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Integer> {
}
