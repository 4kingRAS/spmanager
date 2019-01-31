package com.eking.spmanager.controller;

import com.eking.spmanager.DAO.GoodsTypeDAO;
import com.eking.spmanager.entity.Goods;
import com.eking.spmanager.entity.GoodsType;
import com.eking.spmanager.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-30
 * @Description  商品详情列表
 **/

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    GoodsTypeDAO gtDAO;

    List<Goods> glist;

    @RequestMapping(method = RequestMethod.GET)
    public String getUserList(ModelMap map) {
        glist = goodsService.findAllGoods();
        List<GoodsType> gtlist = gtDAO.findAll();

        map.addAttribute("gtList", gtlist);
        map.addAttribute("goodsList", glist);
        return "goodsList";
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String freshList(ModelMap map) {
        map.addAttribute("goodsList", glist);
        return "goodsList::goodsTable";
    }


    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String getList(@RequestBody GoodsType t, ModelMap map) {
        GoodsType gt = gtDAO.findById(t.getId()).get();
        glist = goodsService.findByType(gt.getName());

        return "Success";
    }

}
