package com.eking.spmanager.implement;

import com.eking.spmanager.entity.UserGroup;
import com.eking.spmanager.DAO.UserGroupDAO;
import com.eking.spmanager.service.UserGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-23
 * @Description
 **/

@Service
public class UserGroupServiceImpl implements UserGroupService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserGroupServiceImpl.class);

    @Autowired
    private UserGroupDAO userGroupDAO;

    @Transactional
    @Override
    public void addGroup(UserGroup userGroup) {
        userGroupDAO.save(userGroup);
        LOGGER.info("\n EKlog|| NEW GROUP - " + userGroup.toString());
    }

    @Override
    public List<UserGroup> findAllGroup() {
        // TODO Auto-generated method stub
        LOGGER.info("\n EKlog|| FIND ALL GROUP  ]]");
        return (List<UserGroup>) userGroupDAO.findAll();
    }

    @Override
    public UserGroup findById(Integer id) {
        LOGGER.info("\n EKlog|| GROUP | FIND BY ID - " + id);
        return userGroupDAO.findById(id).get();//容器对象，加个get()
    }

    @Transactional
    @Override
    public void delete(UserGroup userGroup) {
        userGroupDAO.delete(userGroup);
        LOGGER.info("\n EKlog|| GROUP | DELETE - " + userGroup.toString());
    }

    @Transactional
    @Override
    public void update(UserGroup userGroup) {
        userGroupDAO.save(userGroup);
        LOGGER.info("\n EKlog|| GROUP | UPDATE - " + userGroup.toString());
    }

    public UserGroup findByName(String name) {
        LOGGER.info("\n EKlog|| GROUP | FIND BY NAME - " + name);
        return userGroupDAO.findByName(name);
    }

}
