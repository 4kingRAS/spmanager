package com.eking.spmanager.DAO;

import com.eking.spmanager.entity.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-29
 * @Description
 **/

public interface GoodsDAO extends JpaRepository<Goods, Integer> {
    Goods findByName(String name);
    /**
     * Spring Data Jpa会根据实体类的属性名字以及方法名自动实现该方法；
     * 在实体类中声明@NamedQuery注解，findByName方法会使用@NamedQuery注解标注的查询语句去查询
     * */
    List<Goods> findByType(String type);
    Page<Goods> findAll(Specification<Goods> name, Pageable pageable);
}
