package com.yevay.remy.core.facade;

import com.yevay.remy.view.api.dto.JwtRequest;
import com.yevay.remy.view.api.dto.JwtResponse;

import javax.servlet.http.HttpServletRequest;

public interface JwtAuthFacade {

    JwtResponse createAuthToken(JwtRequest jwtRequest);

    void auth(String token, HttpServletRequest request);

    boolean validateToken(String token);

}
