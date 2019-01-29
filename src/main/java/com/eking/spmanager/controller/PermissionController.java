package com.eking.spmanager.controller;

import com.eking.spmanager.Utils.Utils;
import com.eking.spmanager.Utils.UtilsImpl;
import com.eking.spmanager.entity.Permission;
import com.eking.spmanager.entity.UserGroup;
import com.eking.spmanager.service.PmsService;
import com.eking.spmanager.service.RoleService;
import com.eking.spmanager.service.UserGroupService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @Autowired
    PmsService permissionService;

    @Autowired
    Utils utils;

    /**
     *  获取群组列表
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getUserList(ModelMap map) {
        UserGroup ug = new UserGroup();
        map.addAttribute("ugmodel", ug);
        map.addAttribute("groupList", utils.findAllGPRole());
        return "groupList";
    }

    @RequestMapping(value = "/fresh", method = RequestMethod.GET)
    public String freshList(ModelMap map) {
        map.addAttribute("groupList", utils.findAllGPRole());
        return "groupList::grouptb";
    }

    /**
     *  add usergroup
     *  通过 ajax POST 请求得到json，注解ResponseBody，从而不用返回页面
     *  通过 @ModelAttribute 绑定参数，也可以通过 Param 从页面中传递参数
     */
    @ResponseBody
    @RequestMapping(params = "add", method = RequestMethod.POST)
    public String postGroupForm(@RequestBody String str,
                                BindingResult bindingResult)
                                throws Exception {
        if (bindingResult.hasErrors()) {
            return "ERROR";
        }
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(str);

        UserGroup group = mapper.convertValue(node.get("UserGroup"), UserGroup.class);
        Integer rid = mapper.convertValue(node.get("role"), Integer.class);

        group.setCount(1);
        ugService.addGroup(group);
        Integer gid = ugService.findByName(group.getName()).getId();

        Permission pms = new Permission();
        pms.setRoleid(rid);
        pms.setGroupid(gid);
        permissionService.addPermission(pms);
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

        group.setCount(1);
        ugService.update(group);
        return "update " + group.toString() + " Success";
    }

}
