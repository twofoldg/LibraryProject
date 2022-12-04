package com.uni.project.library.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private DataSource dataSource;

    /*
    Използваме го за да настроим,че сайта ще работи през login форма, които ще имат достъп до сайта,
    както и пренасочването след login/logout
       */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .successForwardUrl("/books")
                .and()
                .logout()
                .permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login");
        return http.build();
    }

    /*
     Използваме го за конфигурация на username, password и роля на user-ите, които ще имат достъп до сайта
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT email as username, password as password, 1 as enabled "
                        + "FROM users "
                        + "WHERE email = ?")
                .authoritiesByUsernameQuery("SELECT u.email as username, r.role as role " +
                        "FROM ROLES r " +
                        "JOIN user_roles ur on ur.role_id = r.id " +
                        "JOIN users u on ur.user_id = u.id "
                        + "where email = ?")
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}