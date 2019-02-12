package com.eking.spmanager.controller;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-18
 * @Description
 **/

import com.eking.spmanager.Utils.Tools;
import com.eking.spmanager.entity.User;
import com.eking.spmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller         // Controller ,could ignore the notation - ResponseBody
@RequestMapping("/")
public class MainController {

    @Autowired
    Tools utils;

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
    public String offline(Principal principal, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().contains("cart")) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
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
