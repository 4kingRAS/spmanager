package com.eking.spmanager.service;

import com.eking.spmanager.domain.OrderDetail;

import java.util.List;

/**
 * @Author Yulin.Wang
 * @Date 2019-02-14
 * @Description
 **/

public interface OrderDetailService {
    void addOrderDetail(OrderDetail od);
    List<OrderDetail> findByOrderId(Integer id);
}
