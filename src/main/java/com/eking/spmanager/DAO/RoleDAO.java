package com.eking.spmanager.DAO;

import com.eking.spmanager.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-24
 * @Description
 **/

public interface RoleDAO extends JpaRepository<Role, Integer> {
}
