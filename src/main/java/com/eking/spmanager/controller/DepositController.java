package com.eking.spmanager.controller;

import com.eking.spmanager.Utils.Box;
import com.eking.spmanager.Utils.Tools;
import com.eking.spmanager.domain.DepositLog;
import com.eking.spmanager.domain.Orders;
import com.eking.spmanager.service.DepoLogService;
import com.eking.spmanager.service.OrderService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author Yulin.Wang
 * @Date 2019-02-18
 * @Description
 **/

@Controller
@RequestMapping("/depository")
public class DepositController {
    private static final int G_PAGE = 0;
    private static final int G_SIZE = 15;

    @Autowired
    DepoLogService logService;
    @Autowired
    OrderService orderService;
    @Autowired
    Tools utils;

    private List<Box> list;
    private Integer gType = -1;

    public void makeList(Integer type) {
        list = new ArrayList<>();
        List<DepositLog> logList;
        if (type != -1) {
            logList = logService.findByType(type.toString());
        } else {
            logList = logService.findAllLog();
        }

        for (DepositLog log : logList) {
            Orders order = orderService.findById(log.getOrderId());
            list.add(new Box(log, order));
        }
    }

    /** 出入库记录列表页面 **/
    @RequestMapping(method = RequestMethod.GET)
    public String getLogList(ModelMap map) {
        try {
            gType = -1; // all
            makeList(gType);
            Collections.reverse(list);
            map.addAttribute("datas", utils.convertPage(G_PAGE, G_SIZE, list));

            return "depositLog";
        } catch (RuntimeException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    /** post 分页 **/
    @RequestMapping(method = RequestMethod.POST)
    public String postList(ModelMap map, @RequestParam(value = "page", defaultValue = "0") Integer page) {
        try {
            makeList(gType);
            Collections.reverse(list);
            map.addAttribute("datas", utils.convertPage(page, G_SIZE, list));

            return "depositLog::depositLog";
        } catch (RuntimeException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    /** 只在选择类别时，改变list **/
    @ResponseBody
    @RequestMapping(value = "/type", method = RequestMethod.POST)
    public String getListByType(ModelMap map,
                                @RequestParam(value = "type", defaultValue = "-1") String type) {
        gType = type.equals("in") ? 0 : 1;
        return "Success";
    }

}
