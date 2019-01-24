package com.eking.spmanager.entity;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-21
 * @Description user Data Access Object, merge in entity package
 **/
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDAO extends JpaRepository<User, Integer> {
    public User findByUsername(String username);
}
