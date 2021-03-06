package com.beril.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username,password,enabled from users where username=?")
                .authoritiesByUsernameQuery(
                        "select username,userrole from users where username=?").passwordEncoder(new BCryptPasswordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/library/users").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/library/books").hasAnyRole("ADMIN","USER")
                .antMatchers(HttpMethod.POST, "/library/books").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/library/books").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE ,"/library/books").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }

}
