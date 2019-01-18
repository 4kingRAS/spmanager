package com.eking.spmanager.dao;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-18
 * @Description user Data Access Object
 **/
import org.springframework.data.repository.CrudRepository;

import com.eking.spmanager.entity.User;

public interface UserDAO extends CrudRepository<User, Integer>{
}
