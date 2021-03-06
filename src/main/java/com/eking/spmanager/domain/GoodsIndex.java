package com.eking.spmanager.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-29
 * @Description
 **/

@Entity
public class GoodsIndex implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gindex_id")
    private Integer id;

    @Column(name = "goods_id", nullable = false)
    private Integer goodsid;

    @Column(name = "goods_count")
    private Integer count;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
