package com.eking.spmanager.controller;

import com.eking.spmanager.dao.GoodsIdxDAO;
import com.eking.spmanager.dao.GoodsTypeDAO;
import com.eking.spmanager.Utils.Box;
import com.eking.spmanager.Utils.Tools;
import com.eking.spmanager.domain.Goods;
import com.eking.spmanager.domain.GoodsType;
import com.eking.spmanager.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
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
    private Integer gType = -1;

    @Autowired
    GoodsService goodsService;

    @Autowired
    GoodsTypeDAO gtDAO;

    @Autowired
    GoodsIdxDAO gIdxDAO;

    @Autowired
    Tools utils;

    public List<Box> makelist(Integer type) {
        List<Box> list = new ArrayList<>();
        List<Goods> glist;
        if (type == -1) {
            glist = goodsService.findAllGoods();
        }
        else {
            GoodsType gt = gtDAO.findById(type).get();
            glist = goodsService.findByType(gt.getName());
        }
        for (Goods g: glist) {
            Integer count = gIdxDAO.findByGoodsid(g.getId()).getCount();
            list.add(new Box(g, count));
        }
        return list;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String loadList(ModelMap map) {

        map.addAttribute("gtList", gtDAO.findAll());
        map.addAttribute("datas", utils.convertPage(G_PAGE, G_SIZE, makelist(-1)));
        return "goodsList";
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String freshList(ModelMap map) {
        //map.addAttribute("goodsList", glist);
        return "goodsList::goodsTable";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String postList(ModelMap map, @RequestParam(value = "page", defaultValue = "0") Integer page) {
        try {
            map.addAttribute("datas",
                    utils.convertPage(page, G_SIZE, makelist(gType)));
            return "goodsList::goodsTable";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/bytype", method = RequestMethod.POST)
    public String getListByType(ModelMap map, @RequestParam(value = "type", defaultValue = "-1") Integer type) {
        gType = type;
        return "Success";
    }

    @RequestMapping(value = "/addGM", method = RequestMethod.GET)
    public String getGTModal(ModelMap map) {
        map.addAttribute("gtList", gtDAO.findAll());
        return "modalGoodsInfo";
    }

    @RequestMapping(value = "/addCart", method = RequestMethod.GET)
    public String getCartModal(ModelMap map, @RequestParam(value = "gid") Integer gid) {

        map.addAttribute("goods", goodsService.findById(gid));
        return "modalAddCart";
    }

    @ResponseBody
    @RequestMapping(value = "/addCart", method = RequestMethod.POST)
    public String postCartModal(ModelMap map, @RequestParam(value = "gid") Integer gid,
                                @RequestParam(value = "amount") Integer amount,
                                @RequestParam(value = "price") double price,
                                HttpServletResponse response) {
        /**URLEncoder.encode(value,"utf-8") 解决中文乱码问题**/
        Cookie cookie = new Cookie("cart" + gid, gid + "-" + amount + "-" + price);
        cookie.setPath("/"); // 跨域 记得 setDomain
        response.addCookie(cookie);
        return "SUCCESS";
    }

    @ResponseBody
    @RequestMapping(params = "add", method = RequestMethod.POST)
    public String postGoods(@RequestBody @Valid Goods goods, BindingResult bindingResult) {
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
