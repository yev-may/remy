package com.yevay.remy.core.facade;

import com.yevay.remy.view.api.dto.JwtRequest;
import com.yevay.remy.view.api.dto.JwtResponse;

public interface JwtAuthFacade {

    JwtResponse createAuthToken(JwtRequest jwtRequest);

}
