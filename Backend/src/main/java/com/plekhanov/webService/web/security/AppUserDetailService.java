package com.plekhanov.webService.web.security;

import com.plekhanov.webService.persistence.dao.UserDao;
import com.plekhanov.webService.persistence.dao.UserRoleDao;
import com.plekhanov.webService.persistence.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * Сервис возвращающий Права и доступы пользователя по имени
 */
@Service("AppUserDetailService")
public class AppUserDetailService implements UserDetailsService {

    @Autowired
    UserDao userDao;
    @Autowired
    UserRoleDao userRoleDao;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userDao.findByName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User " + userName + " not found!");
        }

        user.setAuthorities(userRoleDao.findRolesByUserId(user.getId()));
        return user;
    }


}
