package com.eking.spmanager.service;

import com.eking.spmanager.domain.Permission;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-29
 * @Description
 **/

public interface PmsService {
    public Permission findByGroupid(Integer id);
    public void addPermission(Permission p);
}
