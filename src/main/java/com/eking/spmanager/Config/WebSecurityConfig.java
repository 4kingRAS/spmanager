package com.eking.spmanager.config;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-22
 * @Description  使用Spring security实现的拦截器和验证，自己写url规则，然后根据数据库权限表分配。
 *              适合小项目，大项目还是自己实现安全好。
 **/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * UserDetailService:  spring security user class
     * make a class extends it .
     */
    @Autowired
    private DefUserDetail userDetailsService;

    /**
     * UserDetail 返回user的信息 ，注意Autowired
     */
    UserDetailsService DefUserDetail(){ //注册UserDetailsService 的bean
        return new DefUserDetail();
    }

    /**
     * configure authentication
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new DefPasswordEncoder()); //user Details Service验证
    }

    /**
     * configure http
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                /**
                 * .antMatchers(xxx).access("hasRole('ADMIN') and hasRole('USER')")  与的效果
                 * 用于组合权限
                 */
                .antMatchers("/css/**").permitAll()
                .antMatchers("/images/*").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/manager/**").hasRole("HNA")
                .antMatchers("/user").hasRole("HNA")
                .antMatchers("/goods/**").hasAnyRole("HNA", "BUYER", "CHECKER", "AGENT")
                .antMatchers("/order/**").hasAnyRole("HNA", "BUYER", "AGENT")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/", true)
                .permitAll() //登录页面用户任意访问
                .and()
                .logout().logoutUrl("/logout").permitAll()
                .and()
                .logout().deleteCookies("JSESSIONID");

        http.csrf().disable();
        //http.headers().frameOptions().disable();
    }

}