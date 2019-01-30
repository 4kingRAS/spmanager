package com.eking.spmanager.implement;

import com.eking.spmanager.DAO.GoodsDAO;
import com.eking.spmanager.entity.Goods;
import com.eking.spmanager.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-30
 * @Description
 **/

@Service
public class GoodsServiceImpl implements GoodsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private GoodsDAO goodsDAO;

    @Transactional
    @Override
    public void addGoods(Goods g) {
        goodsDAO.save(g);
        LOGGER.info("\n EKlog|| GOODS | NEW - " + g.toString());
    }

    @Override
    public Goods findByName(String name) {
        return null;
    }

    @Override
    public List<Goods> findAllGoods() {
        LOGGER.info("\n EKlog|| GOODS | FIND ALL ]]");
        return (List<Goods>) goodsDAO.findAll();
    }

    @Transactional
    @Override
    public void update(Goods g) {
        goodsDAO.save(g);
        LOGGER.info("\n EKlog|| GOODS | UPDATE - " + g.toString());
    }

}
