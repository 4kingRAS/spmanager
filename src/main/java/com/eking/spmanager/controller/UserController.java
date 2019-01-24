package com.eking.spmanager.controller;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-21
 * @Description
 **/

import com.eking.spmanager.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eking.spmanager.entity.User;
import com.eking.spmanager.service.UserService;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    Utils utils;

    /**
     *  获取用户列表
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getUserList(ModelMap map) {
        map.addAttribute("userList", userService.findAllUser());
        return "userList";
    }

    /**
     * 显示创建用户表单
     *
     * @param map 添加属性
     * @return 成功页面 在html中
     */
    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public String createUserForm(ModelMap map) {
        User user = new User();
        map.addAttribute("user", user);
        map.addAttribute("action", "reg");
        return "regForm";
    }

    /**
     *  创建用户
     *    处理 "/users" 的 POST 请求，用来获取用户列表
     *    通过 @ModelAttribute 绑定参数，也通过 @RequestParam 从页面中传递参数
     */
    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public String postUserForm(ModelMap map,
                           @ModelAttribute @Valid User user,
                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            map.addAttribute("action", "reg");
            return "regForm";
        }

        try {
            Date d = new Date();
            Timestamp t = new Timestamp(d.getTime());

            user.setGroup(1);
            user.setIsOnline("1");
            user.setLastLogin(t);
            user.setIsActived("1");

            userService.addSingleUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/user/";
    }

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public String check() {
        User user = userService.findByUserName("Mickey");
        System.out.println(user.getUsername());
        return user.getUsername();
    }

}
