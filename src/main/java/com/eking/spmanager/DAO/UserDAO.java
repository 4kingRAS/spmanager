package com.eking.spmanager.DAO;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-21
 * @Description user Data Access Object, merge in entity package
 **/
import com.eking.spmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Integer> {
    public User findByUsername(String username);
}
