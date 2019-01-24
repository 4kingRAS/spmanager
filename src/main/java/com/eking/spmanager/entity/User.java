package com.eking.spmanager.entity;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-21
 * @Description 更新bean 注解boolean用 char（1）
 * GenerationType.IDENTITY 每个实体独立自增
 * AUTO 关联自增
 **/

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@Entity // This tells Hibernate to make a table out of this class
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @NotEmpty(message = "invalid username") // 表单验证
    @Column(name = "user_name", length = 20, nullable = false) // 字段长度
    private String username;

    @NotEmpty(message = "invalid psw")
    @Column(name = "user_password", length = 20, nullable = false)
    private String password;

    @Column(name = "user_corp", length = 50)
    private String corpname;

    @Column(name = "user_isonline", columnDefinition = "char(1) default '0'")
    private String isOnline;

    @Column(name = "user_lastlogin")
    //@Generated(GenerationTime.INSERT)
    private Date lastLogin;

    @Column(name = "group_id")
    private Integer group;

    @Column(name = "is_actived", columnDefinition = "char(1) default '1'")
    private String isActived;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorpname() {
        return corpname;
    }

    public void setCorpname(String corpname) {
        this.corpname = corpname;
    }

    public String getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(String isOnline) {
        this.isOnline = isOnline;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public String getIsActived() {
        return isActived;
    }

    public void setIsActived(String isActived) {
        this.isActived = isActived;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id.toString() +
                ", username=" + username +
                ", ac=" + isActived +
                ", online=" + isOnline +
                '}';
    }

}
