package com.yevay.remy.core.facade.impl;

import com.yevay.remy.core.facade.JwtAuthFacade;
import com.yevay.remy.core.service.JwtTokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class DefaultJwtAuthFacade implements JwtAuthFacade {

    private final JwtTokenService jwtTokenService;
    private final UserDetailsService userDetailsService;

    public DefaultJwtAuthFacade(JwtTokenService jwtTokenService, UserDetailsService userDetailsService) {
        this.jwtTokenService = jwtTokenService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public UsernamePasswordAuthenticationToken auth(String token, HttpServletRequest request) {
        return validateToken(token) ? doAuth(token, request) : null;
    }

    private UsernamePasswordAuthenticationToken doAuth(String token, HttpServletRequest request) {
        String usernameFromToken = jwtTokenService.getUsernameFromToken(token);
        UserDetails user = userDetailsService.loadUserByUsername(usernameFromToken);
        UsernamePasswordAuthenticationToken auth = createAuthToken(user, request);
        SecurityContextHolder.getContext().setAuthentication(auth);
        return auth;
    }

    private UsernamePasswordAuthenticationToken createAuthToken(UserDetails user, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return authToken;
    }

    private boolean validateToken(String token) {
        return jwtTokenService.validateToken(token);
    }
}
