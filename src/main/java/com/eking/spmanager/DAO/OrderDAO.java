package com.eking.spmanager.dao;

import com.eking.spmanager.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author Yulin.Wang
 * @Date 2019-02-10
 * @Description
 **/

public interface OrderDAO extends JpaRepository<Orders, Integer> {
    List<Orders> findByCreateBy(String createBy);
    List<Orders> findByCreateByAndIsActived(String createBy, String isActived);
    List<Orders> findByIsChecked(String isChecked);
    List<Orders> findByIsCheckedNot(String isChecked);
    List<Orders> findByCreateByAndIsChecked(String createBy, String isChecked);
    List<Orders> findByCreateByAndIsCheckedNot(String createBy, String isChecked);
    List<Orders> findByIsActived(String isActived);
    List<Orders> findByIsActivedAndIsChecked(String isActived, String isChecked);
}
