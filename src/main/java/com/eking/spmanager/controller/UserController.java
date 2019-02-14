package com.eking.spmanager.controller;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-21
 * @Description
 **/

import com.eking.spmanager.Utils.Box;
import com.eking.spmanager.Utils.Msg;
import com.eking.spmanager.Utils.Tools;
import com.eking.spmanager.service.UserGroupService;
import com.eking.spmanager.domain.User;
import com.eking.spmanager.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private static final int G_PAGE = 0;
    private static final int G_SIZE = 5;

    @Autowired
    UserService userService;

    @Autowired
    UserGroupService ugService;

    @Autowired
    Tools tools;

    public Page<Box> getComboxPage(int page, int size) {
        List<User> ulist = userService.findAllUser();
        List<Box> list = new ArrayList<>();
        for (User x: ulist) {
            String name = ugService.findById(x.getGroup()).getName();
            list.add(new Box(x, name));
        }

        return tools.convertPage(page, size, list);
    }

    /**
     *  获取用户列表
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getUserList(ModelMap map) {

        map.addAttribute("groupList", ugService.findAllGroup());
        map.addAttribute("datas", getComboxPage(G_PAGE, G_SIZE));
        return "userList";
    }

    @RequestMapping(value = "/fresh", method = RequestMethod.POST)
    public String postFresh(ModelMap map, @RequestParam(value = "page", defaultValue = "0") Integer page,
                            @RequestParam(value = "size", defaultValue = "5") Integer size) {

        map.addAttribute("datas", getComboxPage(page, size));
        return "userList::userTable";
    }

    @RequestMapping(value = "/fresh", method = RequestMethod.GET)
    public String getFresh(ModelMap map) {

        map.addAttribute("datas", getComboxPage(G_PAGE, G_SIZE));
        return "userList::userTable";
    }

    /**
     * 创建用户
     */
    @ResponseBody
    @RequestMapping(params = "add", method = RequestMethod.POST)
    public String postUserForm(@RequestBody @Valid User user,
                                BindingResult bindingResult) {
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

    /** 简易通知类消息 Msg 封禁/解封 **/
    @ResponseBody
    @RequestMapping(params = "deal", method = RequestMethod.POST)
    public String postDealUser(@RequestBody Msg msg) {
        try {
            User user = userService.findById(msg.getId());
            if (user.getIsActived().equals("1")) {
                user.setIsActived("0");
            }
            else {
                user.setIsActived("1");
            }
            userService.update(user);
            return "UPDATE " + user.toString() + " Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: UPDATE USER FAILED!";
        }
    }


    /**
     * LEGACY
     * **/
//        return "redirect:/user/";
//    }

}
