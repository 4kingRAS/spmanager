package com.eking.spmanager.Utils;

import com.eking.spmanager.entity.Role;
import com.eking.spmanager.entity.UserGroup;
import com.eking.spmanager.service.PmsService;
import com.eking.spmanager.service.RoleService;
import com.eking.spmanager.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-22
 * @Description
 **/

@Service
public class UtilsImpl implements Utils {
    
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
        List<idx> idxList = new ArrayList<>();
        List<UserGroup> lg = userGroupService.findAllGroup();
        for (UserGroup x: lg) {
            Role role  = roleService.findById(pmsService.findByGroupid(x.getId()).getRoleid());
            idx i = new idx(x, role);
            idxList.add(i);
        }
        return idxList;
    }

}
