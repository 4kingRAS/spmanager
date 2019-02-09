package com.eking.spmanager.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author Yulin.Wang
 * @Date 2019-02-09
 * @Description
 **/

@Entity
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer id;

    @Column(name = "order_type", nullable = false, columnDefinition = "char(1)")
    private String type;

    @Column(name = "order_detail")
    private Integer priceForSell;

    @Column(name = "price_buy")
    private double priceForBuy;

    @Column(name = "price_market")
    private double priceForMarket;

    @Column(name = "goods_description", length = 180)
    private String description;
}
