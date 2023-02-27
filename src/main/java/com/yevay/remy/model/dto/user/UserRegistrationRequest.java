package com.yevay.remy.model.dto.user;

import com.yevay.remy.model.dto.form.validator.PasswordMatches;
import com.yevay.remy.model.dto.form.validator.ValidEmail;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@PasswordMatches
public class UserRegistrationRequest {
    @ValidEmail
    private String email;
    @Size(min = 4, max = 10, message = "Length must be between 4 and 10 characters")
    private String login;
    @Size(min = 8, message = "Must contain at least 8 characters")
    private String password;
    private String repeatPassword;
}
