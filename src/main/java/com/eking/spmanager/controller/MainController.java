package com.eking.spmanager.controller;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-18
 * @Description
 **/

import com.eking.spmanager.Utils.Utils;
import com.eking.spmanager.entity.User;
import com.eking.spmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller         // Controller ,could ignore the notation - ResponseBody
@RequestMapping("/")
public class MainController {

    @Autowired
    Utils utils;

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap map, Principal principal) {

        //UserDetails userDetails =
                //(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUserName(principal.getName());
        user.setLastLogin(utils.getCurrentTime());
        user.setIsOnline("1");
        userService.update(user);
        map.addAttribute("user", user);
        return "/index";
    }

    @RequestMapping(value = "info", method = RequestMethod.GET)
    public String getUserInfo(Principal principal) {
        return principal.getName();
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @ResponseBody
    @RequestMapping(params = "off", method = RequestMethod.POST)
    public String offline(Principal principal) {
        User user = userService.findByUserName(principal.getName());
        user.setIsOnline("0");
        userService.update(user);
        return "OFF LINE";
    }

    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public String logout() {
        return "logout";
    }

}
