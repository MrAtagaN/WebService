package com.plekhanov.webService.web.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@Qualifier("MyAuthProvider")
public class MyAuthProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        authentication.isAuthenticated();


        return new UsernamePasswordAuthenticationToken("ad", "sad", null);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
