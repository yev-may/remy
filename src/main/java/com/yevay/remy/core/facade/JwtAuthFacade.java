package com.yevay.remy.core.facade;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.servlet.http.HttpServletRequest;

public interface JwtAuthFacade {

    UsernamePasswordAuthenticationToken auth(String token, HttpServletRequest request);

}
