package com.eking.spmanager.service;

import com.eking.spmanager.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-18
 * @Description User service layer
 **/

public interface UserService {
    void addSingleUser(User user);
    User findById(Integer id);
    User findByUserName(String username);
    List<User> findAllUser();
    Page<User> findAllUserInPage(Integer page, Integer size);
    void update(User user);
}
