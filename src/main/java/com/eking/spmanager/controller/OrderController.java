package com.eking.spmanager.controller;

import com.eking.spmanager.dao.DepositLogDAO;
import com.eking.spmanager.dao.GoodsIdxDAO;
import com.eking.spmanager.Utils.Box;
import com.eking.spmanager.Utils.Tools;
import com.eking.spmanager.domain.*;
import com.eking.spmanager.service.DepoLogService;
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
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
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

    class Info {
        public String name;
        public Integer amount;
    }

    @Autowired
    GoodsService goodsService;
    @Autowired
    GoodsIdxDAO gIdxDAO;
    @Autowired
    DepoLogService depoLogService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderDetailService detailService;
    @Autowired
    Tools utils;

    List<Orders> ordersList;
    Role role;

    /** 订单列表页面 **/
    @RequestMapping(method = RequestMethod.GET)
    public String getOrderList(@RequestParam(value = "type", defaultValue = "-1") Integer type,
                                    ModelMap map, Principal principal) {
        try {
            String name = principal.getName();
            role = utils.getRole(name);

            if (utils.isCheckerEmployee(role.getId())) {
                switch (type) {
                    case 1 : {
                        // done
                        ordersList = orderService.findByIsCheckedNot("0");
                        break;
                    }
                    case 0 : {
                        // PENDING
                        ordersList = orderService.findByIsChecked("0");
                        break;
                    }
                    default : {
                        // all
                        ordersList = orderService.findAllOrder();
                        break;
                    }
                }
                map.addAttribute("isChecker", "true");

            } else {
                switch (type) {
                    case 0 : {
                        // canceled
                        ordersList = orderService.findByCreateByAndIsActived(name, "0");
                        break;
                    }
                    case 1 : {
                        // passed or denied
                        ordersList = orderService.findByCreateByAndIsCheckedNotAndIsActived(
                                                        name, "0","1");
                        break;
                    }
                    case 2 : {
                        // PENDING
                        ordersList = orderService.findByCreateByAndIsChecked(name, "0");
                        break;
                    }
                    default : {
                        // all of not canceled
                        ordersList = orderService.findByCreateByAndIsActived(name, "1");
                        break;
                    }
                }
                map.addAttribute("isChecker", "false");
            }

            Collections.reverse(ordersList);
            map.addAttribute("isBuyer",
                    utils.isBuyerEmployee(role.getId()) ? "true" : "false");
            map.addAttribute("datas", utils.convertPage(G_PAGE, G_SIZE, ordersList));
            return "orderList";
        } catch (RuntimeException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    /** post 分页 **/
    @RequestMapping(method = RequestMethod.POST)
    public String postList(ModelMap map, @RequestParam(value = "page", defaultValue = "0") Integer page) {
        try {
            map.addAttribute("isChecker",
                    utils.isCheckerEmployee(role.getId()) ? "true" : "false");
            map.addAttribute("isBuyer",
                    utils.isBuyerEmployee(role.getId()) ? "true" : "false");
            map.addAttribute("datas", utils.convertPage(page, G_SIZE, ordersList));

            return "orderList";
        } catch (RuntimeException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    /** 获得订单明细 **/
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public String getOrderDetail(@RequestParam(value = "order") Integer oid, ModelMap map) {

        try {
            List<OrderDetail> details = detailService.findByOrderId(oid);
            List<Box> list = new ArrayList<>();
            for (OrderDetail od: details) {
                Info i = new Info();
                i.amount = gIdxDAO.findByGoodsid(od.getGoodsId()).getCount();
                i.name = goodsService.findById(od.getGoodsId()).getName();
                list.add(new Box(od, i));
            }
            map.addAttribute("details", list);
            return "orderListPanel";
        } catch (RuntimeException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    /** 处理订单 **/
    @ResponseBody
    @RequestMapping(value = "/deal", method = RequestMethod.POST)
    public String dealOrder(@RequestParam(value = "order") Integer oid,
                            @RequestParam(value = "opt") String opt, Principal principal) {

        try {
            Orders order = orderService.findById(oid);
            order.setCheckBy(principal.getName());
            order.setCheckAt(utils.getCurrentTime());
            if (opt.equals("pass")) {
                order.setIsChecked("1");

                List<OrderDetail> list = detailService.findByOrderId(oid);
                for(OrderDetail orderDetail : list) {
                    Integer amount = orderDetail.getCount();
                    Integer gid = orderDetail.getGoodsId();
                    GoodsIndex goodsIndex = gIdxDAO.findByGoodsid(gid);
                    if (order.getType().equals("0")) {
                        goodsIndex.setCount(goodsIndex.getCount() + amount);
                    } else {
                        if (goodsIndex.getCount() - amount < 0) {
                            return "failed";
                        }
                        goodsIndex.setCount(goodsIndex.getCount() - amount);
                    }
                    depoLogService.makeDepositLog(order, orderDetail);
                }
            }
            if (opt.equals("deny")) { order.setIsChecked("2"); }

            orderService.update(order);
            return "DONE";
        } catch (RuntimeException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    /** 取消订单 **/
    @ResponseBody
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public String cancelOrder(@RequestParam(value = "order") Integer oid) {
        try {
            Orders order = orderService.findById(oid);
            order.setCheckBy("USER CANCELED");
            order.setCheckAt(utils.getCurrentTime());
            order.setIsChecked("3");
            order.setIsActived("0");

            List<OrderDetail> list = detailService.findByOrderId(oid);
            for(OrderDetail orderDetail : list) {
                orderDetail.setIsActived("0");
                detailService.update(orderDetail);
            }

            orderService.update(order);
            return "DONE";
        } catch (RuntimeException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    /** 购物车页面 **/
    @RequestMapping(value = "/orderMaker", method = RequestMethod.GET)
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

            return "orderMaker";
        } catch (RuntimeException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    /** 购物车-添加订单 **/
    @ResponseBody
    @RequestMapping(value = "/orderMaker", params = "make", method = RequestMethod.POST)
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

    /** 购物车-添加子订单 **/
    @ResponseBody
    @RequestMapping(value = "/orderMaker", params = "makeSub", method = RequestMethod.POST)
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

    /** 购物车-删除子订单 **/
    @ResponseBody
    @RequestMapping(value = "/orderMaker/clear", method = RequestMethod.POST)
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
