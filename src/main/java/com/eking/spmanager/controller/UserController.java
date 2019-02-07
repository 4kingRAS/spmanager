package com.eking.spmanager.controller;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-21
 * @Description
 **/

import com.eking.spmanager.Utils.Msg;
import com.eking.spmanager.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.eking.spmanager.Utils.Utils;
import com.eking.spmanager.Utils.UtilsImpl;
import com.eking.spmanager.entity.User;
import com.eking.spmanager.service.UserService;

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
    Utils utils;

    public Page<UtilsImpl.Combox> getComboxPage(int page, int size) {
        List<User> ulist = userService.findAllUser();
        List<UtilsImpl.Combox> clist = new ArrayList<>();
        for (User x: ulist) {
            String gname = ugService.findById(x.getGroup()).getName();
            clist.add(utils.combineList(x, gname));
        }

        return utils.convertPage(page, size, clist);
    }

    /**
     *  获取用户列表
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getUserList(ModelMap map) {

        Page<UtilsImpl.Combox> datas = getComboxPage(G_PAGE, G_SIZE);
        map.addAttribute("groupList", ugService.findAllGroup());
        map.addAttribute("datas", datas);
        return "userList";
    }

    @RequestMapping(value = "/fresh", method = RequestMethod.POST)
    public String postFresh(ModelMap map, @RequestParam(value = "page", defaultValue = "0") Integer page,
                            @RequestParam(value = "size", defaultValue = "5") Integer size) {

        Page<UtilsImpl.Combox> datas = getComboxPage(page, size);
        map.addAttribute("datas", datas);
        return "userList::userTable";
    }

    @RequestMapping(value = "/fresh", method = RequestMethod.GET)
    public String getFresh(ModelMap map) {

        Page<UtilsImpl.Combox> datas = getComboxPage(G_PAGE, G_SIZE);
        map.addAttribute("datas", datas);
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
//
//    @RequestMapping(value = "/reg", method = RequestMethod.GET)
//    public String createUserForm(ModelMap map) {
//        User user = new User();
//        map.addAttribute("user", user);
//        map.addAttribute("action", "reg");
//        return "regForm";
//    }
//
//
//    @RequestMapping(value = "/reg", method = RequestMethod.POST)
//    public String postUserForm(ModelMap map,
//                           @ModelAttribute @Valid User user,
//                           BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors()) {
//            map.addAttribute("action", "reg");
//            return "regForm";
//        }
//
//        try {
//
//            user.setGroup(1);
//            user.setIsOnline("1");
//            user.setLastLogin(utils.getCurrentTime());
//            user.setIsActived("1");
//
//            userService.addSingleUser(user);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return "redirect:/user/";
//    }

}
