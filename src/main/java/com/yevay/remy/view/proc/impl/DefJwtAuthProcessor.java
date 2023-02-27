package com.yevay.remy.view.proc.impl;

import com.yevay.remy.core.service.AuthService;
import com.yevay.remy.core.service.JwtTokenService;
import com.yevay.remy.model.dto.jwt.JwtRequest;
import com.yevay.remy.model.dto.jwt.JwtResponse;
import com.yevay.remy.view.proc.JwtAuthProcessor;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DefJwtAuthProcessor implements JwtAuthProcessor {

    private final AuthService authService;
    private final JwtTokenService jwtTokenService;

    @Override
    public JwtResponse process(JwtRequest jwtRequest) {
        UserDetails user = authService.auth(jwtRequest.getUsername(), jwtRequest.getPassword());
        String token = jwtTokenService.generateToken(user);
        return new JwtResponse(token);
    }
}
