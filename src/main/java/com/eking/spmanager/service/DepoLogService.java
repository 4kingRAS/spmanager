package com.eking.spmanager.service;

import com.eking.spmanager.domain.DepositLog;
import com.eking.spmanager.domain.OrderDetail;
import com.eking.spmanager.domain.Orders;

import java.util.List;

/**
 * @Author Yulin.Wang
 * @Date 2019-02-19
 * @Description
 **/

public interface DepoLogService {
    Integer addLog(DepositLog log);
    void update(DepositLog log);
    DepositLog findById(Integer id);
    List<DepositLog> findAllLog();
    DepositLog makeDepositLog(Orders order, OrderDetail detail);
}
