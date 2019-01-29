package com.eking.spmanager.DAO;

import com.eking.spmanager.entity.Deposite;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-29
 * @Description
 **/

public interface DepositeDAO extends JpaRepository<Deposite, Integer> {
}
