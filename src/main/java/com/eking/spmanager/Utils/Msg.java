package com.eking.spmanager.Utils;

/**
 * @Author Yulin.Wang
 * @Date 2019-02-01
 * @Description  POJO class for transfer simple message
 **/

public class Msg {

    private Integer id;
    private String name;
    private Integer count;

    public Msg() {}  // must have

    public Msg(Integer id, String name, Integer count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
