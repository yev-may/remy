package com.yevay.remy.core.facade.impl;

import com.yevay.remy.core.facade.JwtTokenFacade;
import com.yevay.remy.core.service.JwtTokenService;
import com.yevay.remy.model.dto.jwt.JwtRequest;
import com.yevay.remy.model.dto.jwt.JwtResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class DefaultJwtTokenFacade implements JwtTokenFacade {

    private final JwtTokenService jwtTokenService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authManager;

    public DefaultJwtTokenFacade(JwtTokenService jwtTokenService, UserDetailsService userDetailsService,
                                 AuthenticationManager authManager) {
        this.jwtTokenService = jwtTokenService;
        this.userDetailsService = userDetailsService;
        this.authManager = authManager;
    }

    @Override
    public JwtResponse getToken(JwtRequest jwtRequest) {
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
}
