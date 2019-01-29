package com.eking.spmanager.implement;

import com.eking.spmanager.DAO.UserDAO;
import com.eking.spmanager.entity.User;
import com.eking.spmanager.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-18
 * @Description
 **/

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDAO userDAO;

    @Transactional
    @Override
    public void addSingleUser(User user) {
        userDAO.save(user);
        LOGGER.info("\n EKlog|| USER | NEW - " + user.toString());
    }

    @Override
    public User findByUserName(String username) {
        LOGGER.info("\n EKlog|| USER | FIND BY NAME - " + username);
        return userDAO.findByUsername(username);
    }

    @Override
    public List<User> findAllUser() {
        // TODO Auto-generated method stub
        LOGGER.info("\n EKlog|| USER | FIND ALL |");
        return (List<User>) userDAO.findAll();

    }
}
