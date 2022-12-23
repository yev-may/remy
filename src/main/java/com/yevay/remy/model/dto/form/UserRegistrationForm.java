package com.yevay.remy.model.dto.form;

import com.yevay.remy.model.dto.form.validator.PasswordMatches;
import com.yevay.remy.model.dto.form.validator.ValidEmail;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@PasswordMatches
public class UserRegistrationForm {
    @ValidEmail
    private String email;
    @Size(min=4, max=10, message="Length must be between 4 and 10 characters")
    private String login;
    @Min(value=8, message = "Must contain at least 8 characters")
    private String password;
    private String repeatPassword;
}
