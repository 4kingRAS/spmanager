package com.eking.spmanager.implement;

import com.eking.spmanager.entity.Permission;
import com.eking.spmanager.DAO.PermissionDAO;
import com.eking.spmanager.service.PmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-29
 * @Description
 **/

@Service
public class PmsServiceImpl implements PmsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsServiceImpl.class);

    @Autowired
    private PermissionDAO pd;

    @Override
    public Permission findByGroupid(Integer id) {
        LOGGER.info("\n EKlog|| Permission | FIND BY GROUPID - " + id);
        return pd.findByGroupid(id);
    }

    @Transactional
    @Override
    public void addPermission(Permission p) {
        pd.save(p);
        LOGGER.info("\n EKlog|| NEW GROUP - " + p.toString());
    }

}
