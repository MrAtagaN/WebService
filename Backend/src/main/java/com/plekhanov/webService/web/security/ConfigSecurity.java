package com.plekhanov.webService.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * antMatchers() - маска URL
 * permitAll - разрешено всем
 *
 * loginPage - URL для аутентификации
 * loginProcessingUrl - URL для принятия логина и пароля
 * defaultSuccessUrl - URL после авторизации
 *
 *
 *
 * UserDetails - Права и доступы пользователя
 * GrantedAuthority - Роли пользователя
 *
 * UserDetailsService - Сервис возвращающий UserDetails пользователя по имени
 *
 *
 * https://www.youtube.com/watch?v=HLSmjZ5vN0w
 * Справка: https://www.baeldung.com/spring-security-login
 */
@Configuration
@EnableWebSecurity
public class ConfigSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("AppUserDetailService")
    UserDetailsService userDetailsService;

    /**
     * Настройка защищенных эндпойнтов
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/", "/home").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login.html")
//                .permitAll()
//                .loginProcessingUrl("/perform_login")
//                .permitAll()
//                .failureUrl("/login.html?error=true")
//                .permitAll()
//                .defaultSuccessUrl("/", true)
//                .and()
//                .logout()
//                .permitAll();

        http.csrf().disable()
                .authorizeRequests()
                //.antMatchers("/admin/**").hasRole("ADMIN")
                //.antMatchers("/anonymous*").anonymous()
                .antMatchers("/").permitAll()
                .antMatchers("/*.js").permitAll()
                .antMatchers("/*.html").permitAll()
                .antMatchers("/*.css").permitAll()
                .antMatchers("/login.html").permitAll() //TODO поменять
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")//TODO поменять
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", true)
                //.failureUrl("/login.html?error=true")
                //.failureHandler(authenticationFailureHandler())
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID");
                //.logoutSuccessHandler(logoutSuccessHandler());
    }

    /**
     * Конфигурация AuthenticationManager
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
