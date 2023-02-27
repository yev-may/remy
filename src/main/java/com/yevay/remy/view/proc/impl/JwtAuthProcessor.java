package com.yevay.remy.view.proc.impl;

import com.yevay.remy.core.service.AuthService;
import com.yevay.remy.core.service.JwtTokenService;
import com.yevay.remy.model.dto.token.TokenRequest;
import com.yevay.remy.model.dto.token.TokenResponse;
import com.yevay.remy.view.proc.TokenProcessor;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JwtAuthProcessor implements TokenProcessor {

    private final AuthService authService;
    private final JwtTokenService jwtTokenService;

    @Override
    public TokenResponse process(TokenRequest request) {
        UserDetails user = authService.auth(request.getLogin(), request.getPassword());
        String token = jwtTokenService.generateToken(user);
        return new TokenResponse(token);
    }
}
