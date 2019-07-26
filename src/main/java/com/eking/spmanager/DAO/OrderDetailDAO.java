package com.eking.spmanager.dao;

import com.eking.spmanager.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author Yulin.Wang
 * @Date 2019-02-10
 * @Description
 **/

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Integer> {
    List<OrderDetail> findByOrderId(Integer id);
}
