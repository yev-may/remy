package com.yevay.remy.model.dto.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class JwtResponse implements Serializable {
    private final String jwtToken;
}
