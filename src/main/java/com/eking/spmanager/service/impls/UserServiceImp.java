package com.eking.spmanager.service.impls;

import com.eking.spmanager.dao.UserDAO;
import com.eking.spmanager.entity.User;
import com.eking.spmanager.service.UserService;
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
    @Autowired
    private UserDAO userRepo;

    @Override
    public void addSingleUser(User user) {
        // TODO Auto-generated method stub
        userRepo.save(user);
    }

    @Override
    public List<User> findAllUser() {
        // TODO Auto-generated method stub
        return (List<User>) userRepo.findAll();

    }
}
