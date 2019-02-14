package com.eking.spmanager.controller;

import com.eking.spmanager.dao.GoodsIdxDAO;
import com.eking.spmanager.Utils.Box;
import com.eking.spmanager.Utils.Tools;
import com.eking.spmanager.domain.Goods;
import com.eking.spmanager.domain.OrderDetail;
import com.eking.spmanager.domain.Orders;
import com.eking.spmanager.service.GoodsService;

import com.eking.spmanager.service.OrderDetailService;
import com.eking.spmanager.service.OrderService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
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
    OrderService oService;

    @Autowired
    OrderDetailService odService;

    @Autowired
    Tools utils;

    /** For debug cookies **/
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

    @RequestMapping(value = "/makeOrder", method = RequestMethod.GET)
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

    @ResponseBody
    @RequestMapping(value = "/makeOrder", params = "make", method = RequestMethod.POST)
    public String postOrder(@RequestBody @Valid Orders orders,
                            BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "ERROR";
        }

        try {
            orders.setCreateBy(principal.getName());
            orders.setComment("submitted!");
            orders.setCreateAt(utils.getCurrentTime());
            orders.setIsActived("1");
            orders.setIsChecked("0");

            return oService.addOrder(orders).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: CREATE ORDER FAILED!";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/makeOrder", params = "makeSub", method = RequestMethod.POST)
    public String postSubOrder(@RequestBody String orderList, HttpServletRequest request,
                               HttpServletResponse response, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ERROR";
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(orderList);

            Integer count = mapper.convertValue(node.findValue("Amount"), Integer.class);
            for (int i = 0; i < count.intValue(); i++) {
                OrderDetail od = mapper.convertValue(node.get(i).findValue("OrderDetail"), OrderDetail.class);
                od.setIsActived("1");
                odService.addOrderDetail(od);
            }

            Cookie[] cookies = request.getCookies();
            if(cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().contains("cart")) {
                        cookie.setMaxAge(0);
                        cookie.setPath("/");
                        response.addCookie(cookie);
                    }
                }
            }
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: CREATE ORDER DETAIL FAILED!";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/makeOrder/clear", method = RequestMethod.POST)
    public String clearItem(@RequestParam(value = "gid") Integer gid, HttpServletRequest request,
                               HttpServletResponse response) {
        try {
            Cookie[] cookies = request.getCookies();
            if(cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().contains("cart" + gid.toString())) {
                        cookie.setMaxAge(0);
                        cookie.setPath("/");
                        response.addCookie(cookie);
                    }
                }
            }
            return "CLEAR";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: CLEAR COOKIE WRONG!";
        }
    }

}
