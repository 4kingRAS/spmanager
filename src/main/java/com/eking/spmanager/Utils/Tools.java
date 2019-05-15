package com.eking.spmanager.Utils;

import com.eking.spmanager.domain.*;
import com.eking.spmanager.service.PmsService;
import com.eking.spmanager.service.RoleService;
import com.eking.spmanager.service.UserGroupService;
import com.eking.spmanager.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    private static final int ROLE_ID_BUYER = 2;
    private static final int ROLE_ID_CHECKER = 3;

    @Autowired
    UserService userService;

    @Autowired
    UserGroupService userGroupService;

    @Autowired
    RoleService roleService;

    @Autowired
    PmsService pmsService;

    public Role getRole(String username) {
        Integer gid = userService.findByUserName(username).getGroup();
        return roleService.findById(pmsService.findByGroupid(gid).getRoleid());
    }

    public boolean isBuyerEmployee(Integer rid) {
        return rid == ROLE_ID_BUYER;
    }

    public boolean isCheckerEmployee(Integer rid) {
        return rid == ROLE_ID_CHECKER;
    }


    public void clearCookies(String key, HttpServletRequest request,
                             HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().contains(key)) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }
    }

    public List<Box> getGroupWithRole() {
        List<Box> list = new ArrayList<>();
        List<UserGroup> lg = userGroupService.findAllGroup();
        for (UserGroup x : lg) {
            Permission p = pmsService.findByGroupid(x.getId());
            Role role = roleService.findById(p.getRoleid());

            Box box = new Box(x, role);
            list.add(box);
        }

        return list;
    }

    public <T> Page<T> convertPage(int page, int size, List<T> clist) {

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "id");
        int start = (int) pageable.getOffset();
        int end = (start + pageable.getPageSize()) > clist.size() ? clist.size() : (start + pageable.getPageSize());
        Page<T> datas = new PageImpl<>(clist.subList(start, end), pageable, clist.size());

        return datas;
    }

    public Timestamp getCurrentTime() {
        Date d = new Date();
        return new Timestamp(d.getTime());
    }
}
