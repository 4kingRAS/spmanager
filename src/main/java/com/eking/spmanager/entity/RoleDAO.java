package com.eking.spmanager.entity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-24
 * @Description
 **/

public interface RoleDAO extends JpaRepository<Role, Integer> {
    public Role findByName(String name);
}
