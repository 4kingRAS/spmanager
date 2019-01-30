package com.eking.spmanager.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-30
 * @Description
 **/

@Entity
public class GoodsType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gt_id")
    private Integer id;

    @Column(name = "goods_type", length = 50, nullable = false, unique = true)
    private String name;

}
