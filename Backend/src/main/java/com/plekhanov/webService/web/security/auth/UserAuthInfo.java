package com.plekhanov.webService.web.security.auth;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Set;

/**
 * Права и доступы пользователя
 */
@Data
@Builder
public class UserAuthInfo implements UserDetails {

    private String password;
    private String username;
    private Set<Role> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;


}
