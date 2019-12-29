package com.plekhanov.webService.entities;

import com.plekhanov.webService.web.security.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Set;

/**
 * Права и доступы пользователя
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthInfo extends BaseEntity<Long> implements UserDetails {

    private Long id;
    private String password;
    private String username;
    private Set<Role> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;


}
