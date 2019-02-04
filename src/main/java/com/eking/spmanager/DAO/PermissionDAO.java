package com.eking.spmanager.DAO;

import com.eking.spmanager.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-24
 * @Description
 **/

public interface PermissionDAO extends JpaRepository<Permission, Integer> {
    Permission findByGroupid(Integer id);
}
