package com.eking.spmanager.implement;

import com.eking.spmanager.dao.OrderDAO;
import com.eking.spmanager.domain.Orders;
import com.eking.spmanager.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Yulin.Wang
 * @Date 2019-02-10
 * @Description
 **/

@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
    private static String entity = "ORDER";

    @Autowired
    private OrderDAO orderDAO;

    @Transactional
    public Integer addOrder(Orders o) {
        orderDAO.save(o);
        LOGGER.info("[eKing log]: {}  {}: - {}", entity, "NEW ", o.toString());
        return o.getId();
    }

    @Transactional
    public void update(Orders order) {
        orderDAO.save(order);
        LOGGER.info("[eKing log]: {}  {}: - {}", entity, "UPDATE ", order.toString());
    }

    public Orders findById(Integer id) {
        LOGGER.info("[eKing log]: {}  : - {}", entity, "FIND BY ID", id.toString());
        return orderDAO.findById(id).get();
    }

    public List<Orders> findAllOrder() {
        LOGGER.info("[eKing log]: {}  : - {}", entity, "FIND ALL");
        return orderDAO.findAll();
    }

    public List<Orders> findByCreateBy(String createBy) {
        LOGGER.info("[eKing log]: {}  {}: - {}", entity, "FIND CRT BY", createBy);
        return orderDAO.findByCreateBy(createBy);
    }

    public List<Orders> findByCreateByAndIsActived(String createBy, String isActived) {
        LOGGER.info("[eKing log]: {}  {}: - {}", entity, "FIND act and cby", createBy);
        return orderDAO.findByCreateByAndIsActived(createBy, isActived);
    }

    public List<Orders> findByIsChecked(String isChecked) {
        LOGGER.info("[eKing log]: {}  {}: - {}", entity, "FIND CHECKED", isChecked);
        return orderDAO.findByIsChecked(isChecked);
    }

    public List<Orders> findByIsCheckedNot(String isChecked) {
        LOGGER.info("[eKing log]: {}  {}: - {}", entity, "FIND CHECKED not", isChecked);
        return orderDAO.findByIsCheckedNot(isChecked);
    }

    public List<Orders> findByCreateByAndIsCheckedNot(String createBy, String isChecked){
        LOGGER.info("[eKing log]: {}  {}: - {}", entity, "FIND DONE and cby", createBy);
        return orderDAO.findByCreateByAndIsCheckedNot(createBy, isChecked);
    }

    public List<Orders> findByCreateByAndIsChecked(String createBy, String isChecked){
        LOGGER.info("[eKing log]: {}  {}: - {}", entity, "FIND PENDING and cby", createBy);
        return orderDAO.findByCreateByAndIsChecked(createBy, isChecked);
    }

    public List<Orders> findByIsActived(String isActived) {
        LOGGER.info("[eKing log]: {}  {}: - {}", entity, "FIND ACT", isActived);
        return orderDAO.findByIsActived(isActived);
    }

}
