package com.eking.spmanager.entity;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-23
 * @Description
 **/

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupDAO extends JpaRepository<UserGroup, Integer> {

    public UserGroup findByName(String name);

}
