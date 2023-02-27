package com.yevay.remy.model.dto.token;

import lombok.Data;

import java.io.Serializable;

@Data
public class TokenRequest implements Serializable {
    private String login;
    private String password;
}
