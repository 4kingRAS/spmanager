package com.eking.spmanager.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @Author Yulin.Wang
 * @Date 2019-02-09
 * @Description
 **/

@Entity
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer id;

    /** 0: buy 1: sell **/
    @Column(name = "order_type", nullable = false, columnDefinition = "char(1)")
    private String type;

    @Column(name = "order_price")
    private double price;

    @Column(name = "order_amount")
    private Integer amount;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "check_at")
    private Date checkAt;

    /** fk user **/
    @Column(name = "create_by")
    private Integer createBy;

    @Column(name = "check_by")
    private Integer checkBy;

    @Column(name = "order_comment", length = 100)
    private String comment;

    @Column(name = "is_checked", columnDefinition = "char(1) default '0'")
    private String isChecked;

    @Column(name = "is_actived", columnDefinition = "char(1) default '1'")
    private String isActived;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getCheckAt() {
        return checkAt;
    }

    public void setCheckAt(Date checkAt) {
        this.checkAt = checkAt;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Integer getCheckBy() {
        return checkBy;
    }

    public void setCheckBy(Integer checkBy) {
        this.checkBy = checkBy;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }

    public String getIsActived() {
        return isActived;
    }

    public void setIsActived(String isActived) {
        this.isActived = isActived;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id.toString()+
                ", type=" + type +
                ", cid=" + createBy +
                ", check=" + isChecked +
                '}';
    }

}
