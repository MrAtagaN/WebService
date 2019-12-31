package com.plekhanov.webService.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.GenericFilterBean;
import javax.servlet.Filter;

/**
 * antMatchers() - маска URL
 * permitAll - разрешено всем
 *
 * loginPage - URL для аутентификации
 * loginProcessingUrl - URL для принятия логина и пароля
 * defaultSuccessUrl - URL после авторизации
 *
 * loginPage("/login.html") - редирект на эндпойнт при неодходимости авторизации (должен быть доступен всем)
 * failureUrl("/login.html?error=true") - редирект на эндпойнт при ошибке логина (должен быть доступен всем)
 * logoutUrl("/logout") - эндпойнт для разлогина
 *
 *
 * auth.userDetailsService(UserDetailsService) - Поменять UserDetailsService
 * {@link UserDetailsService} - Сервис, который находит информацию о правах и доступах пользователя по его имени
 *
 * UserDetails - Права и доступы пользователя
 * GrantedAuthority - Роли пользователя
 *
 *
 * auth.authenticationProvider(AuthenticationProvider) - Добавить AuthenticationProvider
 * {@link AuthenticationProvider} - Интерфейс для кастомной аунтефикации
 * Если метод authenticate() возвращает null, то обработка передается следующему AuthenticationProvider
 *
 *
 * {@link Authentication} - Информация об аунтефикации пользователя. После успешной аутентификации кладется в контекст
 * SecurityContextHolder
 * Если метод isAuthenticated() возвращает true, то запрос не будет проверяться другими AuthenticationProvider
 * SecurityContextHolder.getContext().setAuthentication(Authentication) - Явно поместить Authentication в контекст
 *
 *
 * http.addFilter(Filter) - Добавить кастомный фильтр
 * {@link Filter}- Интерфейс для кастомного фильтра
 *
 *
 * Хабр https://habr.com/ru/post/346628/
 * Видео https://www.youtube.com/watch?v=vSwJoHTkl_o&list=PLaWfw53gNyzaDTEmrlCCj1jjqr6770Nnp&index=18
 * Enable HTTPS/SSL  https://www.youtube.com/watch?v=HLSmjZ5vN0w
 * Справка: https://www.baeldung.com/spring-security-login
 */
@Configuration
@EnableWebSecurity
public class ConfigSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("AppUserDetailService")
    UserDetailsService userDetailsService;


    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring()
                .antMatchers("/")
                .antMatchers("/*.js")
                .antMatchers("/*.html")
                .antMatchers("/*.css")
                .antMatchers("/login.html") //TODO поменять
                .antMatchers("/*.ico");
    }

    /**
     * Настройка защищенных эндпойнтов
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")//TODO поменять
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", true)
                //.failureUrl("/login.html?error=true") //редирект на эндпойнт при ошибке логина
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
