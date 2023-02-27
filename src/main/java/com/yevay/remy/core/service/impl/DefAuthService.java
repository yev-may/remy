package com.yevay.remy.core.service.impl;

import com.yevay.remy.core.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DefAuthService implements AuthService {

    private final AuthenticationManager authManager;
    private final UserDetailsService userDetailsService;

    @Override
    public UserDetails auth(String login, String password) {
        Authentication authentication = getAuthentication(login, password);
        return userDetailsService.loadUserByUsername(authentication.getName());
    }

    private Authentication getAuthentication(String login, String password) {
        try {
            return authManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
        } catch (DisabledException e) {
            throw new RuntimeException("User disabled", e);
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Invalid credits", e);
        }
    }
}
