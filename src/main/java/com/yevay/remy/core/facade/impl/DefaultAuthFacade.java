package com.yevay.remy.core.facade.impl;

import com.yevay.remy.core.facade.AuthFacade;
import com.yevay.remy.model.dto.UserSessionDto;
import com.yevay.remy.model.dto.form.UserLoginForm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

@Component("AuthFacade")
public class DefaultAuthFacade implements AuthFacade {

    private final AuthenticationManager authenticationManager;

    public DefaultAuthFacade(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserSessionDto login(UserLoginForm form) {
        UsernamePasswordAuthenticationToken token = createToken(form);
        Authentication auth = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(auth);

        return UserSessionDto.builder()
                .id(RequestContextHolder.currentRequestAttributes().getSessionId()).build();
    }

    private UsernamePasswordAuthenticationToken createToken(UserLoginForm form) {
        return new UsernamePasswordAuthenticationToken(form.getEmail(), form.getPassword());
    }
}
