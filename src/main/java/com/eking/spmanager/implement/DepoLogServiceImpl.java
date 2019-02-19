package com.eking.spmanager.implement;

import com.eking.spmanager.Utils.Tools;
import com.eking.spmanager.dao.DepositLogDAO;
import com.eking.spmanager.domain.DepositLog;
import com.eking.spmanager.domain.OrderDetail;
import com.eking.spmanager.domain.Orders;
import com.eking.spmanager.service.DepoLogService;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Yulin.Wang
 * @Date 2019-02-19
 * @Description
 **/

@Service
public class DepoLogServiceImpl implements DepoLogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
    private static String entity = "ORDER";

    @Autowired
    private DepositLogDAO depositLogDAO;
    @Autowired
    private Tools utils;

    @Transactional
    public Integer addLog(DepositLog log) {
        depositLogDAO.save(log);
        LOGGER.info("[eKing log]: {}  {}: - {}", entity, "NEW - id: ", log.getId());
        return log.getId();
    }

    @Transactional
    public void update(DepositLog log) {
        depositLogDAO.save(log);
    }

    public DepositLog findById(Integer id) {
        return depositLogDAO.findById(id).get();
    }

    public List<DepositLog> findAllLog() {
        LOGGER.info("[eKing log]: {}  : - {}", entity, "FIND ALL");
        return depositLogDAO.findAll();
    }

    public DepositLog makeDepositLog(Orders order, OrderDetail detail) {
        DepositLog log = new DepositLog();
        log.setOrderId(order.getId());
        log.setType(order.getType());
        log.setGoodsId(detail.getGoodsId());
        log.setAmount(detail.getCount());
        log.setTime(utils.getCurrentTime());
        log.setIsActived("1");
        addLog(log);
        return log;
    }

}
