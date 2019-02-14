package com.eking.spmanager.implement;

import com.eking.spmanager.dao.OrderDAO;
import com.eking.spmanager.domain.Orders;
import com.eking.spmanager.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Override
    public Integer addOrder(Orders o) {
        orderDAO.save(o);
        LOGGER.info("[eKing log]: {}  {}: - {}", entity, "NEW ", o.toString());
        return o.getId();
    }
}
