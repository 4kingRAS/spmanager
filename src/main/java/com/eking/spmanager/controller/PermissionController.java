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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping(value = "/fresh", method = RequestMethod.GET)
    public String freshList(ModelMap map) {
        map.addAttribute("groupList", ugService.findAllGroup());
        return "groupList::grouptb";
    }

//    @RequestMapping(value = "/add", method = RequestMethod.GET)
//    public String createUserGroupForm(ModelMap map) {
//        UserGroup userGroup = new UserGroup();
//        map.addAttribute("isagent", value);
//        map.addAttribute("userGroup", userGroup);
//
//        return "addGroup";
//    }

    /**
     *  创建用户
     *    处理 "/manager" 的 POST 请求，用来获取用户列表
     *    通过 @ModelAttribute 绑定参数，也通过 @RequestParam 从页面中传递参数
     */
    @ResponseBody
    @RequestMapping(params = "add", method = RequestMethod.POST)
    public String postGroupForm(@RequestBody UserGroup group)
                                throws Exception {
        //if (bindingResult.hasErrors()) {
            //map.addAttribute("action", "add");
            //return "redirect:/manager/";

        if (group.getIsAgent() != null) {
            group.setIsAgent("0");
        }
        else {
            group.setIsAgent("1");
        }

        group.setCount(1);
        ugService.addGroup(group);

        return "add Success";
    }

    /**
     * 显示创建用户表单
     *
     * @return 成功页面 在html中
     */
    @ResponseBody
    @RequestMapping(params = "delete", method = RequestMethod.POST)
    public String deleteUser(@RequestBody UserGroup group)
                            throws Exception{
        ugService.deleteById(group.getId());
        return "delete Success";
    }

}
