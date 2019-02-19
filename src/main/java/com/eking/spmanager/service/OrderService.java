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
    void update(Orders order);
    Orders findById(Integer id);
    List<Orders> findAllOrder();
    List<Orders> findByCreateBy(String createBy);
    List<Orders> findByCreateByAndIsActived(String createBy, String isActived);
    List<Orders> findByIsChecked(String isChecked);
    List<Orders> findByIsCheckedNot(String isChecked);
    List<Orders> findByCreateByAndIsChecked(String createBy, String isChecked);
    List<Orders> findByCreateByAndIsCheckedNot(String createBy, String isChecked);
    List<Orders> findByCreateByAndIsCheckedNotAndIsActived(
            String createBy, String isChecked, String isActived);
    List<Orders> findByIsActived(String isActived);
}
