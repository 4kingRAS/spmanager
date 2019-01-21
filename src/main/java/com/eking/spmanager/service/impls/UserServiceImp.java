package com.eking.spmanager.service.impls;

import com.eking.spmanager.entity.UserDAO;
import com.eking.spmanager.entity.User;
import com.eking.spmanager.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-18
 * @Description
 **/
@Service
public class UserServiceImp implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImp.class);

    @Autowired
    private UserDAO userRepo;

    @Override
    public void addSingleUser(User user) {
        // TODO Auto-generated method stub
        LOGGER.info("EKlog: new user: " + user.toString());
        userRepo.save(user);
    }

    @Override
    public List<User> findAllUser() {
        // TODO Auto-generated method stub
        LOGGER.info("EKlog: find all: ");
        return (List<User>) userRepo.findAll();

    }
}
