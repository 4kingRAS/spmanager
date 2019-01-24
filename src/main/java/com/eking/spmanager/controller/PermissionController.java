package com.eking.spmanager.controller;

import com.eking.spmanager.Utils.Utils;
import com.eking.spmanager.entity.User;
import com.eking.spmanager.entity.UserGroup;
import com.eking.spmanager.service.UserGroupService;
import com.eking.spmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-23
 * @Description
 **/

@Controller
@RequestMapping("/manager")
public class PermissionController {

    @Autowired
    UserGroupService ugService;

    String value;

    /**
     *  获取群组列表
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getUserList(ModelMap map) {
        map.addAttribute("groupList", ugService.findAllGroup());
        return "groupList";
    }

    /**
     * 显示创建用户表单
     *
     * @param map 添加属性
     * @return 成功页面 在html中
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createUserGroupForm(ModelMap map) {
        UserGroup userGroup = new UserGroup();
        map.addAttribute("isagent", value);
        map.addAttribute("userGroup", userGroup);
        map.addAttribute("action", "add");
        return "addGroup";
    }

    /**
     *  创建用户
     *    处理 "/users" 的 POST 请求，用来获取用户列表
     *    通过 @ModelAttribute 绑定参数，也通过 @RequestParam 从页面中传递参数
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String postGroupForm(ModelMap map,
                           @ModelAttribute @Valid UserGroup group,
                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            map.addAttribute("action", "add");
            return "addGroup";
        }

        if (group.getIsAgent() != null) {
            group.setIsAgent("1");
        }
        else{
            group.setIsAgent("0");
        }

        group.setCount(1);
        ugService.addGroup(group);
        return "redirect:/manager/";
    }
}
