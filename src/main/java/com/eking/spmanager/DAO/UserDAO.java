package com.eking.spmanager.dao;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-21
 * @Description user Data Access Object, merge in domain package
 **/
import com.eking.spmanager.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
