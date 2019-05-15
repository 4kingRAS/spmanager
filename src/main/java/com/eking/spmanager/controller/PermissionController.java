package com.eking.spmanager.controller;

import com.eking.spmanager.Utils.Tools;
import com.eking.spmanager.domain.Permission;
import com.eking.spmanager.domain.UserGroup;
import com.eking.spmanager.service.PmsService;
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
    Tools utils;

    /**
     *  获取群组列表
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getUGList(ModelMap map) {
        try {
            UserGroup ug = new UserGroup();
            map.addAttribute("ugmodel", ug);
            map.addAttribute("groupList", utils.getGroupWithRole());
            return "groupList";
        } catch (RuntimeException e) {
            e.printStackTrace();
            return "Error";
        }
    }

    @RequestMapping(value = "/fresh", method = RequestMethod.GET)
    public String freshList(ModelMap map) {
        try {
            map.addAttribute("groupList", utils.getGroupWithRole());
            return "groupList::groupTable";
        } catch (RuntimeException e) {
            e.printStackTrace();
            return "Error";
        }
    }

    /**
     *  add usergroup
     *  通过 ajax POST 请求得到json，注解ResponseBody，从而不用返回页面
     *  通过 @ModelAttribute 绑定参数，也可以通过 Param 从页面中传递参数
     */
    @ResponseBody
    @RequestMapping(params = "add", method = RequestMethod.POST)
    public String postGroupForm(@RequestBody String str,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ERROR";
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(str);
            UserGroup group = mapper.convertValue(node.get("UserGroup"), UserGroup.class);
            Integer rid = mapper.convertValue(node.get("role"), Integer.class);

            group.setCount(0);
            ugService.addGroup(group);
            Integer gid = ugService.findByName(group.getName()).getId();

            Permission pms = new Permission();
            pms.setRoleid(rid);
            pms.setGroupid(gid);
            permissionService.addPermission(pms);

            return "add " + group.toString() + " Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: CREATE GROUP FAILED!";
        }
    }

    /**
     * delete userGroup
     */
    @ResponseBody
    @RequestMapping(params = "delete", method = RequestMethod.POST)
    public String deleteUG(@RequestBody UserGroup group, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "DELETE ERROR";
        }

        try {
            ugService.delete(ugService.findById(group.getId()));
            return "delete Success";
        } catch (RuntimeException e) {
            e.printStackTrace();
            return "Error";
        }
    }

    /**
     * update userGroup
     */
    @ResponseBody
    @RequestMapping(params = "update", method = RequestMethod.POST)
    public String updateUG(@RequestBody @Valid UserGroup group,
                             BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "UPDATE ERROR";
        }

        try {
            UserGroup temp = ugService.findById(group.getId());
            group.setCount(temp.getCount());
            ugService.update(group);
            return "update " + group.toString() + " Success";
        } catch (RuntimeException e) {
            e.printStackTrace();
            return "Error";
        }
    }

}
