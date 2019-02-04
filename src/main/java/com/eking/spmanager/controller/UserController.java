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


        Pageable pageable = PageRequest.of(0,5, Sort.Direction.ASC,"id");
        int start = (int)pageable.getOffset();
        int end = (start + pageable.getPageSize()) > clist.size() ? clist.size() : (start + pageable.getPageSize());
        Page<UtilsImpl.Combox> datas = new PageImpl<>(clist, pageable, 9);//(clist.subList(start, end), pageable, clist.size());
        map.addAttribute("groupList", ugService.findAllGroup());
        map.addAttribute("datas", datas);
        return "userList";
    }

    @RequestMapping(value = "/fresh", method = RequestMethod.GET)
    public String freshList(ModelMap map, @RequestParam(value = "page", defaultValue = "0") Integer page,
                            @RequestParam(value = "size", defaultValue = "1") Integer size) {
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
