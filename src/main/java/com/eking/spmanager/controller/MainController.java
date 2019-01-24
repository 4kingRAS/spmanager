package com.eking.spmanager.controller;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-18
 * @Description
 **/

import com.eking.spmanager.entity.User;
import com.eking.spmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.eking.spmanager.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller         // Controller ,could ignore the notation - ResponseBody
@RequestMapping("/")
public class MainController {

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

}
