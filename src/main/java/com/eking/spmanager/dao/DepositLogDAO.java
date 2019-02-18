package com.eking.spmanager.dao;

import com.eking.spmanager.domain.DepositLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Yulin.Wang
 * @Date 2019-02-18
 * @Description
 **/

public interface DepositLogDAO extends JpaRepository<DepositLog, Integer> {
}
