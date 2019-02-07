package com.eking.spmanager.controller;

import com.eking.spmanager.DAO.GoodsTypeDAO;
import com.eking.spmanager.Utils.Utils;
import com.eking.spmanager.entity.Goods;
import com.eking.spmanager.entity.GoodsType;
import com.eking.spmanager.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

    private static final int G_PAGE = 0;
    private static final int G_SIZE = 2;

    @Autowired
    GoodsService goodsService;

    @Autowired
    GoodsTypeDAO gtDAO;

    @Autowired
    Utils utils;

    List<Goods> glist;

    @RequestMapping(method = RequestMethod.GET)
    public String getUserList(ModelMap map) {

        glist = goodsService.findAllGoods();
        map.addAttribute("gtList", gtDAO.findAll());
        map.addAttribute("datas", goodsService.findGoodsNoCondition(G_PAGE, G_SIZE));
        return "goodsList";
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String freshList(ModelMap map) {
        //map.addAttribute("goodsList", glist);
        return "goodsList::goodsTable";
    }

    @ResponseBody
    @RequestMapping(value = "/bytype", method = RequestMethod.POST)
    public String postList(ModelMap map, @RequestParam(value = "type", defaultValue = "-1") Integer type) {
        //map.addAttribute("goodsList", glist);
        if (type == -1) {
            glist = goodsService.findAllGoods();
        }
        else {
            GoodsType gt = gtDAO.findById(type).get();
            glist = goodsService.findByType(gt.getName());
        }
        return "Success";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String getList(ModelMap map, @RequestParam(value = "type", defaultValue = "-1") Integer type,
                          @RequestParam(value = "page", defaultValue = "0") Integer page) {
        try {
            map.addAttribute("datas", utils.convertPage(page, G_SIZE, glist));
            return "goodsList::goodsTable";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    @RequestMapping(value = "/addGTM", method = RequestMethod.GET)
    public String getGTModal() {
        return "modalGoodsInfo";
    }

}
