package com.eking.spmanager.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-23
 * @Description 大坑：Group是mysql的关键字，表名字不能用Group！！
 **/

@Entity
public class UserGroup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Integer id;

    @NotEmpty(message = "invalid UserGroup Name")
    @Column(name = "group_name", length = 20, nullable = false)
    private String name;

    @Column(name = "group_isAgent", columnDefinition = "char(1) default '1'")
    private String isAgent;

    @Column(name = "group_count")
    private Integer count;

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

    public String getIsAgent() {
        return isAgent;
    }

    public void setIsAgent(String isAgent) {
        this.isAgent = isAgent;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id.toString()+
                ", name=" + name +
                ", agent=" + isAgent +
                ", count=" + count.toString() +
                '}';
    }

}
