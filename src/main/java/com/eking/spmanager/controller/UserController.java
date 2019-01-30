package com.eking.spmanager.controller;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-21
 * @Description
 **/

import com.eking.spmanager.entity.UserGroup;
import com.eking.spmanager.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.eking.spmanager.Utils.Utils;
import com.eking.spmanager.Utils.UtilsImpl;
import com.eking.spmanager.entity.User;
import com.eking.spmanager.service.UserService;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.*;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserGroupService ugService;

    @Autowired
    Utils utils;

    /**
     *  获取用户列表
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getUserList(ModelMap map) {
        List<User> ulist = userService.findAllUser();
        List<UtilsImpl.Combox> clist = new ArrayList<>();
        for (User x: ulist) {
            String gname = ugService.findById(x.getGroup()).getName();
            clist.add(utils.combineList(x, gname));
        }

        map.addAttribute("groupList", ugService.findAllGroup());
        map.addAttribute("userList", clist);
        return "userList";
    }

    @RequestMapping(value = "/fresh", method = RequestMethod.GET)
    public String freshList(ModelMap map) {
        List<User> ulist = userService.findAllUser();
        List<UtilsImpl.Combox> clist = new ArrayList<>();
        for (User x: ulist) {
            String gname = ugService.findById(x.getGroup()).getName();
            clist.add(utils.combineList(x, gname));
        }
        map.addAttribute("userList", clist);
        return "userList::userTable";
    }

    /**
     * 创建用户
     */
    @ResponseBody
    @RequestMapping(params = "add", method = RequestMethod.POST)
    public String postGroupForm(@RequestBody @Valid User user,
                                BindingResult bindingResult)
            throws Exception {
        if (bindingResult.hasErrors()) {
            return "ERROR";
        }

        try {
            user.setIsOnline("0");
            user.setIsActived("1");
            userService.addSingleUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: CREATE USER FAILED!";
        }

        return "add " + user.toString() + " Success";
    }

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

}
