package com.eking.spmanager.implement;

import com.eking.spmanager.domain.Role;
import com.eking.spmanager.dao.RoleDAO;
import com.eking.spmanager.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-29
 * @Description
 **/

@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);
    private static String entity = "ROLE";

    @Autowired
    RoleDAO roleDAO;

    public Role findById(Integer id) {
        LOGGER.info("[eKing log]: {}  {}: - {}", entity, "FIND BY ID", id.toString());
        return roleDAO.findById(id).get();
    }
}
