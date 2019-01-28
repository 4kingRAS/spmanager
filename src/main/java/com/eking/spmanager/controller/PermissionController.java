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

    /**
     *  add usergroup
     *  通过 ajax POST 请求得到json，注解ResponseBody，从而不用返回页面
     *  通过 @ModelAttribute 绑定参数，也可以通过 Param 从页面中传递参数
     */
    @ResponseBody
    @RequestMapping(params = "add", method = RequestMethod.POST)
    public String postGroupForm(@RequestBody @Valid UserGroup group,
                                BindingResult bindingResult)
                                throws Exception {
        if (bindingResult.hasErrors()) {
            return "ERROR";
        }

        if (group.getIsAgent() != null) {
            group.setIsAgent("0");
        }
        else {
            group.setIsAgent("1");
        }

        group.setCount(1);
        ugService.addGroup(group);

        return "add " + group.toString() + " Success";
    }

    /**
     * delete userGroup
     */
    @ResponseBody
    @RequestMapping(params = "delete", method = RequestMethod.POST)
    public String deleteUser(@RequestBody UserGroup group,
                             BindingResult bindingResult)
                             throws Exception {
        if (bindingResult.hasErrors()) {
            return "DELETE ERROR";
        }
        ugService.delete(ugService.findById(group.getId()));
        return "delete Success";
    }

    /**
     * update userGroup
     */
    @ResponseBody
    @RequestMapping(params = "update", method = RequestMethod.POST)
    public String updateUser(@RequestBody @Valid UserGroup group,
                             BindingResult bindingResult)
            throws Exception {
        if (bindingResult.hasErrors()) {
            return "UPDATE ERROR";
        }

        if (group.getIsAgent() != null) {
            group.setIsAgent("0");
        }
        else {
            group.setIsAgent("1");
        }

        group.setCount(1);
        ugService.update(group);
        return "update " + group.toString() + " Success";
    }

}
