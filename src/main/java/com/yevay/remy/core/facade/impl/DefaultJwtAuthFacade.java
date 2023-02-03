package com.yevay.remy.core.facade.impl;

import com.yevay.remy.core.facade.JwtAuthFacade;
import com.yevay.remy.core.service.JwtTokenService;
import com.yevay.remy.model.dto.jwt.JwtRequest;
import com.yevay.remy.model.dto.jwt.JwtResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
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
    private final AuthenticationManager authManager;

    public DefaultJwtAuthFacade(JwtTokenService jwtTokenService, UserDetailsService userDetailsService,
                                AuthenticationManager authManager) {
        this.jwtTokenService = jwtTokenService;
        this.userDetailsService = userDetailsService;
        this.authManager = authManager;
    }

    @Override
    public JwtResponse createAuthToken(JwtRequest jwtRequest) {
        authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());

        UserDetails user = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtTokenService.generateToken(user);
        return new JwtResponse(token);
    }

    private void authenticate(String username, String password) {
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new RuntimeException("User disabled", e);
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Invalid credits", e);
        }
    }

    @Override
    public void auth(String token, HttpServletRequest request) {
        String usernameFromToken = jwtTokenService.getUsernameFromToken(token);
        UserDetails user = userDetailsService.loadUserByUsername(usernameFromToken);
        UsernamePasswordAuthenticationToken auth = createAuthToken(user, request);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private UsernamePasswordAuthenticationToken createAuthToken(UserDetails user, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return authToken;
    }

    @Override
    public boolean validateToken(String token) {
        return jwtTokenService.validateToken(token);
    }
}
