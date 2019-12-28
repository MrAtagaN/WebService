package com.plekhanov.webService.web.security.auth;

import org.springframework.security.core.GrantedAuthority;

/**
 * Роли пользователя приложения
 */
public enum Role implements GrantedAuthority {

    USER,
    ADMIN,
    UNKNOWN;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
