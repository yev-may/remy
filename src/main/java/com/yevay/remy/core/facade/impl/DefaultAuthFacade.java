package com.yevay.remy.core.facade.impl;

import com.yevay.remy.core.facade.AuthFacade;
import com.yevay.remy.model.dto.form.UserLoginForm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("AuthFacade")
public class DefaultAuthFacade implements AuthFacade {

    private final AuthenticationManager authenticationManager;

    public DefaultAuthFacade(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void login(UserLoginForm form) {
        UsernamePasswordAuthenticationToken token = createToken(form);
        Authentication auth;
        try {
            auth = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid credits");
        }
    }

    private UsernamePasswordAuthenticationToken createToken(UserLoginForm form) {
        return new UsernamePasswordAuthenticationToken(form.getEmail(), form.getPassword());
    }
}
