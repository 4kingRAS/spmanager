package com.eking.spmanager.controller;

import com.eking.spmanager.entity.Goods;
import com.eking.spmanager.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-30
 * @Description
 **/

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @RequestMapping(method = RequestMethod.GET)
    public String getUserList(ModelMap map) {
        List<Goods> glist = goodsService.findAllGoods();

        map.addAttribute("goodsList", glist);
        return "goodsList";
    }

    @RequestMapping(value = "/fresh", method = RequestMethod.GET)
    public String freshList(ModelMap map) {
        return "goodsList::goodsTable";
    }
}
