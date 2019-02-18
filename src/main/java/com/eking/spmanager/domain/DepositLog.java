package com.eking.spmanager.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author Yulin.Wang
 * @Date 2019-02-18
 * @Description
 **/

@Entity
public class DepositLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Integer id;

    @Column(name = "order_id", nullable = false)
    private Integer orderId;

    @Column(name = "goods_id", nullable = false)
    private Integer goodsId;

    @Column(name = "goods_amount")
    private Integer amount;

    @Column(name = "log_type", columnDefinition = "char(1)")
    private String type;

    @Column(name = "log_time")
    private Date time;

    /** 0 deleted 1 active **/
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getIsActived() {
        return isActived;
    }

    public void setIsActived(String isActived) {
        this.isActived = isActived;
    }

}
