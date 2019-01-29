package com.eking.spmanager.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-24
 * @Description 群组与角色对照表
 **/

@Entity
public class Permission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gp_id")
    private Integer id;

    @Column(name="group_id", nullable = false)
    private Integer groupid;

    @Column(name = "role_id", nullable = false)
    private Integer roleid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    @Override
    public String toString() {
        return "PTABLE{" +
                "id=" + id.toString() +
                ", ROLE=" + roleid.toString() +
                ", GROUP=" + groupid.toString() +
                '}';
    }

}
