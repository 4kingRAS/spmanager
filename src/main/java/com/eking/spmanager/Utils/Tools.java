package com.eking.spmanager.Utils;

import com.eking.spmanager.domain.*;
import com.eking.spmanager.service.PmsService;
import com.eking.spmanager.service.RoleService;
import com.eking.spmanager.service.UserGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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
public class Tools {

    private static final Logger LOGGER = LoggerFactory.getLogger(Tools.class);
    private static String entity = "UTILITY";
    
    public class idx {
        public UserGroup ug;
        public Role role;

        public idx(UserGroup ug, Role role) {
            this.ug = ug;
            this.role = role;
        }
    }
    
    @Autowired
    UserGroupService userGroupService;
    
    @Autowired
    RoleService roleService;
    
    @Autowired
    PmsService pmsService;

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


//    public Page<UtilsImpl.Combox> convertPage(int page, int size, List<UtilsImpl.Combox> clist) {
//
//        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC,"id");
//        int start = (int)pageable.getOffset();
//        int end = (start + pageable.getPageSize()) > clist.size() ? clist.size() : (start + pageable.getPageSize());
//        Page<UtilsImpl.Combox> datas = new PageImpl<>(clist.subList(start, end), pageable, clist.size());
//
//        return datas;
//    }


    public <T> Page<T> convertPage(int page, int size, List<T> clist) {

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC,"id");
        int start = (int)pageable.getOffset();
        int end = (start + pageable.getPageSize()) > clist.size() ? clist.size() : (start + pageable.getPageSize());
        Page<T> datas = new PageImpl<>(clist.subList(start, end), pageable, clist.size());

        return datas;
    }

    public Timestamp getCurrentTime() {
        Date d = new Date();
        return new Timestamp(d.getTime());
    }
}
