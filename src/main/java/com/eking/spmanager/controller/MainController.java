package com.eking.spmanager.controller;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-18
 * @Description
 **/

import com.eking.spmanager.entity.User;
import com.eking.spmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.eking.spmanager.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller         // Controller ,could ignore the notation - ResponseBody
@RequestMapping("/")
public class MainController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap map, Principal principal) {
        map.addAttribute("username", principal.getName());
        //UserDetails userDetails =
                //(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUserName(principal.getName());
        Date d = new Date();
        Timestamp t = new Timestamp(d.getTime());

        user.setLastLogin(t);
        user.setIsOnline("1");
        userService.update(user);
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
