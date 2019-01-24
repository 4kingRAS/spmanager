package com.eking.spmanager.service;

import com.eking.spmanager.entity.User;

import java.util.List;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-18
 * @Description User service layer
 **/

public interface UserService {
    public void addSingleUser(User user);
    public User findByUserName(String username);
    public List<User> findAllUser();
}
