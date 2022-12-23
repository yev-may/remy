package com.yevay.remy.model.dto.form;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationForm {
    private String email;
    private String login;
    private String password;
    private String repeatPassword;
}
