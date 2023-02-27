package com.yevay.remy.model.dto.token;

import lombok.Data;

import java.io.Serializable;

@Data
public class TokenResponse implements Serializable {
    private final String token;
}
