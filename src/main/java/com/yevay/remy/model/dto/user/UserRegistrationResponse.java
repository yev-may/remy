package com.yevay.remy.model.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistrationResponse {
    private String login;
}
