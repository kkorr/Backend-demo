package com.amr.project.security;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


//TODO для проверки работы страницы авторизациии регистрации + remember me
@Configuration
@EnableWebSecurity
@ComponentScan("java")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/registration").permitAll().anyRequest().authenticated().and()
                .formLogin().loginPage("/login").permitAll().and()
                .logout().deleteCookies("remember-me").permitAll()
                .and().rememberMe();
    }
}