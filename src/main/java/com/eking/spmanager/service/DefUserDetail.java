package com.eking.spmanager.service;

import com.eking.spmanager.entity.UserGroup;
import com.eking.spmanager.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author Yulin.Wang
 * @Date 2019-01-23
 * @Description
 **/

@Service
public class DefUserDetail implements UserDetailsService {

    @Autowired
    UserService userService;

    @Autowired
    UserGroupService userGroupService;

    public UserDetails loadUserByUsername(String username) { //重写loadUserByUsername 方法获得 userdetails 类型用户

        User user = userService.findByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }

        if(user.getIsActived() == "0")
        {
            throw new UsernameNotFoundException("用户被禁用！");
        }

        try {
            UserGroup userGroup = userGroupService.findById(user.getGroup());
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();

            //用于添加用户的权限。只要把用户权限添加到authorities。

            authorities.add(new SimpleGrantedAuthority(userGroup.getName()));
            System.out.println(userGroup.getName());

            return new org.springframework.security.core.userdetails.User(user.getUsername(),
                    user.getPassword(), authorities);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
