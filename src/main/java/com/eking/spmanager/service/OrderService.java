package com.eking.spmanager.service;

import com.eking.spmanager.domain.Orders;

import java.util.List;

/**
 * @Author Yulin.Wang
 * @Date 2019-02-10
 * @Description
 **/

public interface OrderService {
    Integer addOrder(Orders o);
    List<Orders> findAllOrder();
    List<Orders> findByCreateBy(String createBy);
}
