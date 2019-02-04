package com.eking.spmanager.implement;

import com.eking.spmanager.DAO.UserDAO;
import com.eking.spmanager.entity.Goods;
import com.eking.spmanager.entity.User;
import com.eking.spmanager.entity.UserGroup;
import com.eking.spmanager.service.UserGroupService;
import com.eking.spmanager.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private static String entity = "USER";

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserGroupService ugService;

    @Transactional
    @Override
    public void addSingleUser(User user) {
        userDAO.save(user);
        UserGroup g = ugService.findById(user.getGroup());
        g.setCount(g.getCount() + 1);
        LOGGER.info("[eKing log]: {}  {}: - {}", entity, "NEW ", user.toString());
    }

    @Override
    public User findById(Integer id) {
        try {

            User u = userDAO.findById(id).get();
            LOGGER.info("[eKing log]: {}  {}: - {}", entity, "FIND BY ID", id.toString());
            return u;
        } catch (Exception e) {

            e.printStackTrace();
            LOGGER.error("[eKing log]: {}  {}: - {}", entity, "CANT FIND BY ID", id.toString());
            return null;
        }
    }

    @Override
    public User findByUserName(String username) {
        try {

            User u = userDAO.findByUsername(username);
            LOGGER.info("[eKing log]: {}  {}: - {}", entity, "FIND BY NAME", username);
            return u;
        } catch (Exception e) {

            e.printStackTrace();
            LOGGER.error("[eKing log]: {}  {}: - {}", entity, "CANT FIND BY NAME", username);
            return null;
        }
    }

    @Override
    public List<User> findAllUser() {
        // TODO Auto-generated method stub
        LOGGER.info("[eKing log]: {} : - {}", entity, "FIND ALL");
        return userDAO.findAll();
    }

    @Override
    public Page<User> findAllUserInPage(Integer page, Integer size) {
        //.of method instead of constructor
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "id");
        return userDAO.findAll(pageable);
    }

    @Transactional
    @Override
    public void update(User user) {
        userDAO.save(user);
        LOGGER.info("[eKing log]: {}  {}: - {}", entity, "UPDATE ", user.toString());
    }

}
