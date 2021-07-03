package com.hiberus.employee.directory.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * WebSecurityConfig.
 * 
 * @author acachiguango on 29/06/2021
 * @version 1.0
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Lazy
    @Autowired
    private AuthEntryPoint authEntryPoint;

    @Lazy
    @Autowired
    private AuthAccessDeniedHandler authAccessDeniedHandler;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().addFilterAfter(new AuthFilter(), UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests().antMatchers("/api/v1/auth/login").permitAll().anyRequest().authenticated();
        http.exceptionHandling().accessDeniedHandler(this.authAccessDeniedHandler)
            .authenticationEntryPoint(this.authEntryPoint);
        http.cors();
    }
}
