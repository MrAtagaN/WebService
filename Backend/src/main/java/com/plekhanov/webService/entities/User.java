package com.plekhanov.webService.entities;

import com.plekhanov.webService.web.security.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Права и доступы пользователя
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity<Long> implements UserDetails {

    private Long id;

    //Security info
    private String username;
    private String password;
    private Set<Role> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    //Public info
    private LocalDateTime lastEnter;

}
