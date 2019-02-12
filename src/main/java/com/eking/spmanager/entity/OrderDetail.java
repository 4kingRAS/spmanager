package com.eking.spmanager.entity;

import javax.persistence.*;

/**
 * @Author Yulin.Wang
 * @Date 2019-02-10
 * @Description
 **/

@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id")
    private Integer id;

    @Column(name = "order_id", nullable = false)
    private Integer orderId;

    @Column(name = "goods_id", nullable = false)
    private Integer goodsId;

    @Column(name = "goods_count", nullable = false)
    private Integer count;

    @Column(name = "goods_price", nullable = false)
    private double price;

    @Column(name = "is_actived", columnDefinition = "char(1) default '1'")
    private String isActived;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIsActived() {
        return isActived;
    }

    public void setIsActived(String isActived) {
        this.isActived = isActived;
    }
}
