package com.yevay.remy.core.facade;

import com.yevay.remy.model.dto.jwt.JwtRequest;
import com.yevay.remy.model.dto.jwt.JwtResponse;

public interface JwtTokenFacade {

    JwtResponse getToken(JwtRequest jwtRequest);

}
