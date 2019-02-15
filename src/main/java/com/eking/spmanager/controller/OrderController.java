package com.eking.spmanager.controller;

import com.eking.spmanager.dao.GoodsIdxDAO;
import com.eking.spmanager.Utils.Box;
import com.eking.spmanager.Utils.Tools;
import com.eking.spmanager.domain.*;
import com.eking.spmanager.service.GoodsService;
import com.eking.spmanager.service.OrderDetailService;
import com.eking.spmanager.service.OrderService;

import com.eking.spmanager.service.UserService;
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
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Yulin.Wang
 * @Date 2019-02-11
 * @Description  Orders Operation Class
 **/

@Controller
@RequestMapping("/order")
public class OrderController {

    private static final int G_PAGE = 0;
    private static final int G_SIZE = 5;

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
    OrderService orderService;

    @Autowired
    OrderDetailService detailService;

    @Autowired
    Tools utils;

    @RequestMapping(method = RequestMethod.GET)
    public String initOrderList(ModelMap map, Principal principal) {
        try {
            Role role = utils.getRole(principal.getName());
            if (utils.isCheckerEmployee(role.getId())) {

            } else {

                List<Orders> ordersList = orderService.findByCreateBy(principal.getName());
                map.addAttribute("datas", utils.convertPage(G_PAGE, G_SIZE, ordersList));
                map.addAttribute("isBuyer",
                        utils.isBuyerEmployee(role.getId()) ? "true" : "false");
            }
            return "orderList";
        } catch (RuntimeException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    @RequestMapping(value = "/makeOrder", method = RequestMethod.GET)
    public String getOrderMap(ModelMap map, HttpServletRequest request, Principal principal) {
        Role role = utils.getRole(principal.getName());
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
                map.addAttribute("amount", amount);
                map.addAttribute("sumPrice", sum);
            }

            map.addAttribute("isEmptyCart", list.isEmpty() ? "true" : "false");
            map.addAttribute("isBuyer",
                    utils.isBuyerEmployee(role.getId()) ? "true" : "false");

            return "makeOrder";
        } catch (RuntimeException e) {
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
            Role role = utils.getRole(principal.getName());
            orders.setCreateBy(principal.getName());
            orders.setComment("submitted!");
            orders.setCreateAt(utils.getCurrentTime());
            orders.setIsActived("1");
            orders.setIsChecked("0");

            /** distinguish buyer employee **/
            if (utils.isBuyerEmployee(role.getId())) {
                orders.setType("0");
            } else {
                orders.setType("1");
            }

            return orderService.addOrder(orders).toString();
        } catch (RuntimeException e) {
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
            for (int i = 0; i < count; i++) {
                OrderDetail od = mapper.convertValue(node.get(i).findValue("OrderDetail"), OrderDetail.class);
                od.setIsActived("1");
                detailService.addOrderDetail(od);
            }
            utils.clearCookies("cart", request, response);

            return "ok";
        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR: CREATE ORDER DETAIL FAILED!";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/makeOrder/clear", method = RequestMethod.POST)
    public String clearItem(@RequestParam(value = "gid") Integer gid, HttpServletRequest request,
                               HttpServletResponse response) {
        try {
            utils.clearCookies("cart" + gid.toString(), request, response);
            return "CLEAR";
        } catch (RuntimeException e) {
            e.printStackTrace();
            return "ERROR: CLEAR COOKIE WRONG!";
        }
    }

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

}
