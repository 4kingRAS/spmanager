package com.eking.spmanager.Utils;

import com.eking.spmanager.entity.Permission;
import com.eking.spmanager.entity.Role;
import com.eking.spmanager.entity.UserGroup;
import com.eking.spmanager.service.PmsService;
import com.eking.spmanager.service.RoleService;
import com.eking.spmanager.service.UserGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-22
 * @Description
 **/

@Service
public class UtilsImpl implements Utils {

    private static final Logger LOGGER = LoggerFactory.getLogger(UtilsImpl.class);
    private static String entity = "UTILITY";
    
    public class idx {
        public UserGroup ug;
        public Role role;

        public idx(UserGroup ug, Role role) {
            this.ug = ug;
            this.role = role;
        }
    }

    public class Combox {
        public Object main;
        public Object branch;
    }
    
    @Autowired
    UserGroupService userGroupService;
    
    @Autowired
    RoleService roleService;
    
    @Autowired
    PmsService pmsService;

    public Combox combineList(Object a, Object b) {
        Combox c = new Combox();
        c.main = a;
        c.branch = b;
        return c;
    }

    public List<idx> findAllGPRole() {
        try {
            List<idx> idxList = new ArrayList<>();
            List<UserGroup> lg = userGroupService.findAllGroup();
            for (UserGroup x: lg) {

                Permission p = pmsService.findByGroupid(x.getId());
                Role role = roleService.findById(p.getRoleid());
                idx i = new idx(x, role);
                idxList.add(i);
            }

            return idxList;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("[eKing log]: {}  : - {}", entity, "CANT FIND ALL ROLE");
            return null;
        }
    }

    public Timestamp getCurrentTime() {
        Date d = new Date();
        return new Timestamp(d.getTime());
    }
}
