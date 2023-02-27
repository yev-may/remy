package com.yevay.remy.view.proc;

import com.yevay.remy.model.dto.jwt.JwtRequest;
import com.yevay.remy.model.dto.jwt.JwtResponse;

public interface JwtAuthProcessor {

    JwtResponse process(JwtRequest jwtRequest);

}
