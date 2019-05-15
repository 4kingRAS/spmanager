package com.eking.spmanager.implement;

import com.eking.spmanager.dao.GoodsDAO;
import com.eking.spmanager.domain.Goods;
import com.eking.spmanager.service.GoodsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-30
 * @Description
 **/

@Service
public class GoodsServiceImpl implements GoodsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private static String entity = "GOODS";

    @Autowired
    private GoodsDAO goodsDAO;

    @Transactional
    @Override
    public void addGoods(Goods g) {
        goodsDAO.save(g);
        LOGGER.info("[eKing log]: {}  {}: - {}", entity, "NEW ", g.toString());
    }

    @Override
    public Goods findById(Integer id) {
        LOGGER.info("[eKing log]: {}  {}: - {}", entity, "FIND BY ID", id.toString());
        return goodsDAO.findById(id).get();
    }

    @Override
    public Goods findByName(String name) {
        LOGGER.info("[eKing log]: {}  {}: - {}", entity, "FIND BY NAME", name);
        return goodsDAO.findByName(name);
    }

    @Override
    public List<Goods> findByType(String type) {
        LOGGER.info("[eKing log]: {}  {}: - {}", entity, "FIND BY TYPE", type);
        return goodsDAO.findByType(type);
    }

    public List<Goods> findByNameIsLike(String name) {
        LOGGER.info("[eKing log]: {}  {}: - {}", entity, "FIND LIKE: ", name);
        return goodsDAO.findByNameIsLike("%"+name+"%");
    }

    @Override
    public List<Goods> findAllGoods() {
        LOGGER.info("[eKing log]: {}  : - {}", entity, "FIND ALL");
        return goodsDAO.findAll();
    }

    @Transactional
    @Override
    public void update(Goods g) {
        goodsDAO.save(g);
        LOGGER.info("[eKing log]: {}  {}: - {}", entity, "UPDATE", g.toString());
    }

    @Override
    public Page<Goods> findGoodsNoCondition(Integer page, Integer size) {
        //.of method instead of constructor
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "id");
        return goodsDAO.findAll(pageable);
    }

    @Override
    public Page<Goods> findGoodsWithCondition(Integer page, Integer size, final Goods goods) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "id");
        Page<Goods> goodsPage = goodsDAO.findAll(new Specification<Goods>(){
            @Override
            public Predicate toPredicate(Root<Goods> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(null != goods.getName() && !"".equals(goods.getName())){
                    list.add(criteriaBuilder.equal(root.get("name").as(String.class), goods.getName()));
                }
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, pageable);
        return goodsPage;
    }

}
