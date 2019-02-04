package com.eking.spmanager.service;

import com.eking.spmanager.entity.UserGroup;

import java.util.List;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-23
 * @Description
 **/

public interface UserGroupService {
    void addGroup(UserGroup userGroup);
    void delete(UserGroup userGroup);
    void update(UserGroup userGroup);
    UserGroup findById(Integer id);
    List<UserGroup> findAllGroup();
    UserGroup findByName(String name);
}
