package com.eking.spmanager.implement;

import com.eking.spmanager.domain.UserGroup;
import com.eking.spmanager.dao.UserGroupDAO;
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
    private static String entity = "USER_GROUP";

    @Autowired
    private UserGroupDAO userGroupDAO;

    @Transactional
    @Override
    public void addGroup(UserGroup userGroup) {
        userGroupDAO.save(userGroup);
        LOGGER.info("[eKing log]: {}  {}: - {}", entity, "NEW", userGroup.toString());
    }

    @Override
    public List<UserGroup> findAllGroup() {
        LOGGER.info("[eKing log]: {}  : - {}", entity, "FIND ALL");
        return userGroupDAO.findAll();
    }

    @Override
    public UserGroup findById(Integer id) {
        try {

            UserGroup ug = userGroupDAO.findById(id).get();//容器对象，加个get()
            LOGGER.info("[eKing log]: {}  {}: - {}", entity, "FIND BY ID", id.toString());
            return ug;
        } catch (Exception e) {

            e.printStackTrace();
            LOGGER.error("[eKing log]: {}  {}: - {}", entity, "CANT FIND BY ID", id.toString());
            return null;
        }
    }

    @Transactional
    @Override
    public void delete(UserGroup userGroup) {
        userGroupDAO.delete(userGroup);
        LOGGER.info("[eKing log]: {}  {}: - {}", entity, "DELETE", userGroup.toString());
    }

    @Transactional
    @Override
    public void update(UserGroup userGroup) {
        userGroupDAO.save(userGroup);
        LOGGER.info("[eKing log]: {}  {}: - {}", entity, "UPDATE", userGroup.toString());
    }

    public UserGroup findByName(String name) {
        try {

            UserGroup ug = userGroupDAO.findByName(name);
            LOGGER.info("[eKing log]: {}  {}: - {}", entity, "FIND BY NAME", name);
            return ug;
        } catch (Exception e) {

            e.printStackTrace();
            LOGGER.error("[eKing log]: {}  {}: - {}", entity, "CANT FIND BY NAME", name);
            return null;
        }
    }

}
