package com.eking.spmanager.dao;

import com.eking.spmanager.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-24
 * @Description
 **/

public interface PermissionDAO extends JpaRepository<Permission, Integer> {
    Permission findByGroupid(Integer id);
}
