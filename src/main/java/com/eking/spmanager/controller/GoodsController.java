package com.eking.spmanager.controller;

import com.eking.spmanager.DAO.GoodsTypeDAO;
import com.eking.spmanager.Utils.Utils;
import com.eking.spmanager.entity.Goods;
import com.eking.spmanager.entity.GoodsType;
import com.eking.spmanager.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public String loadList(ModelMap map) {

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

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String postList(ModelMap map, @RequestParam(value = "type", defaultValue = "-1") Integer type,
                          @RequestParam(value = "page", defaultValue = "0") Integer page) {
        try {
            map.addAttribute("datas", utils.convertPage(page, G_SIZE, glist));
            return "goodsList::goodsTable";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/bytype", method = RequestMethod.POST)
    public String getTypeList(ModelMap map, @RequestParam(value = "type", defaultValue = "-1") Integer type) {
        if (type == -1) {
            glist = goodsService.findAllGoods();
        }
        else {
            GoodsType gt = gtDAO.findById(type).get();
            glist = goodsService.findByType(gt.getName());
        }
        return "Success";
    }

    @RequestMapping(value = "/addGM", method = RequestMethod.GET)
    public String getGTModal(ModelMap map) {
        map.addAttribute("gtList", gtDAO.findAll());
        return "modalGoodsInfo";
    }

    @ResponseBody
    @RequestMapping(params = "add", method = RequestMethod.POST)
    public String postGoods(@RequestBody @Valid Goods goods,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ERROR";
        }

        try {
            String type = gtDAO.findById(Integer.valueOf(goods.getType())).get().getName();
            goods.setType(type);
            goodsService.addGoods(goods);
            return "add " + goods.toString() + " Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: CREATE GOODS FAILED!";
        }
    }

}
