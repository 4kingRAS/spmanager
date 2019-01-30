package com.eking.spmanager.service;

import com.eking.spmanager.entity.User;

import java.util.List;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-18
 * @Description User service layer
 **/

public interface UserService {
    void addSingleUser(User user);
    User findByUserName(String username);
    List<User> findAllUser();
    void update(User user);
}
