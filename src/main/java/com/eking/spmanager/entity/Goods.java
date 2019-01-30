package com.eking.spmanager.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-29
 * @Description
 **/

@Entity
public class Goods implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goods_id")
    private Integer id;

    @Column(name = "goods_name", length = 100, nullable = false, unique = true)
    private String name;

    @Column(name = "goods_type", length = 50, nullable = false)
    private String type;

    @Column(name = "price_sell")
    private double priceForSell;

    @Column(name = "price_buy")
    private double priceForBuy;

    @Column(name = "price_market")
    private double priceForMarket;

    @Column(name = "goods_description", length = 180)
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPriceForSell() {
        return priceForSell;
    }

    public void setPriceForSell(double priceForSell) {
        this.priceForSell = priceForSell;
    }

    public double getPriceForBuy() {
        return priceForBuy;
    }

    public void setPriceForBuy(double priceForBuy) {
        this.priceForBuy = priceForBuy;
    }

    public double getPriceForMarket() {
        return priceForMarket;
    }

    public void setPriceForMarket(double priceForMarket) {
        this.priceForMarket = priceForMarket;
    }

    public void setPriceForMarket(float priceForMarket) {
        this.priceForMarket = priceForMarket;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id.toString()+
                ", name=" + name +
                ", type=" + type +
                '}';
    }

}
