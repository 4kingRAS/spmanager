package com.eking.spmanager.controller;

import com.eking.spmanager.DAO.GoodsIdxDAO;
import com.eking.spmanager.Utils.Box;
import com.eking.spmanager.Utils.Tools;
import com.eking.spmanager.entity.Goods;
import com.eking.spmanager.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Yulin.Wang
 * @Date 2019-02-11
 * @Description
 **/

@Controller
@RequestMapping("/order")
public class OrderController {

    class Cart {
        public Integer amount;
        public double price;
        public Integer depo;
    }

    @Autowired
    GoodsService goodsService;

    @Autowired
    GoodsIdxDAO gIdxDAO;

    @Autowired
    Tools utils;

    @ResponseBody
    @RequestMapping(value = "/cookie", method = RequestMethod.GET)
    public String getCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().contains("cart")) {
                    return cookie.getValue();
                }
            }
        }
        return "NULL";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getOrderMap(ModelMap map, HttpServletRequest request) {
        Integer amount = 0;
        double sum = 0;
        try {
            List<Box> list = new ArrayList<>();
            Cookie[] cookies = request.getCookies();
            if(cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().contains("cart")) {
                        Cart c = new Cart();
                        String[] attr = cookie.getValue().split("-");

                        Goods g = goodsService.findById(Integer.valueOf(attr[0]));
                        c.amount = Integer.valueOf(attr[1]);
                        c.price = Double.valueOf(attr[2]);
                        c.depo = gIdxDAO.findByGoodsid(g.getId()).getCount();
                        if (c.amount > c.depo) {
                            c.amount = c.depo;
                        }
                        amount = amount + 1;
                        sum = sum + c.amount * c.price;
                        list.add(new Box(g, c));
                    }
                }
                map.addAttribute("order", list);
            }
            if (list.isEmpty()) {
                map.addAttribute("isNullCart", "true");
            }
            else {
                map.addAttribute("isNullCart", "false");
            }
            map.addAttribute("amount", amount);
            map.addAttribute("sumPrice", sum);

            return "makeOrder";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

}
