package com.yevay.remy.model.dto.form;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginForm {
    private String email;
    private String password;
}
