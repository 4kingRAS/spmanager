package com.eking.spmanager.entity;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-18
 * @Description
 **/

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity // This tells Hibernate to make a table out of this class
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "invalid name") // 表单验证
    @Column(length = 20) // 字段长度
    private String name;

    @NotEmpty(message = "invalid psw") // 表单验证
    @Column(length = 20) // 字段长度
    private String password;

    private String email;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
