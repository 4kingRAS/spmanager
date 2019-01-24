package com.eking.spmanager;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-18
 * @Description
 **/

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class SpmanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpmanagerApplication.class, args);
    }

}

