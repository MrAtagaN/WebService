package com.plekhanov.webService.web.security;

import com.plekhanov.webService.dao.UserDao;
import com.plekhanov.webService.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


/**
 * Сервис возвращающий Права и доступы пользователя по имени
 */
@Service("AppUserDetailService")
public class AppUserDetailService implements UserDetailsService {

    @Autowired
    UserDao userDao;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Set<Role> roles = new HashSet<>();
        roles.add(Role.ADMIN);

        User user = User.builder().
                username(userName).
                password("$2a$10$LPlDRvtwnCdZfq53Xzi.3.1emXw5VFyTLDMOq2gTM7Cf1cqf7xRgC"). //admin
                authorities(roles).
                accountNonExpired(true).
                accountNonLocked(true).
                credentialsNonExpired(true).
                enabled(true).
                build();

        //TODO реализовать
//        User user = userDao.findByName(userName);
//        if (user == null) {
//            throw new UsernameNotFoundException("User " + userName + " not found!");
//        }

        return user;
    }


}
