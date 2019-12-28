package com.plekhanov.webService.web.security;

import com.plekhanov.webService.web.security.auth.Role;
import com.plekhanov.webService.web.security.auth.UserAuthInfo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


/**
 * Сервис возвращающий Права и доступы пользователя по имени
 */
@Service("AppUserDetailService")
public class AppUserDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException { //TODO реализовать
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ADMIN);

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        return UserAuthInfo.builder().
                username(userName).
                password(encoder.encode("admin")).
                authorities(roles).
                accountNonExpired(true).
                accountNonLocked(true).
                credentialsNonExpired(true).
                enabled(true).
                build();
    }

}
