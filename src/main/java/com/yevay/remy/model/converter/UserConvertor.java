package com.yevay.remy.model.converter;

import com.yevay.remy.model.domain.User;
import com.yevay.remy.model.dto.UserDto;
import com.yevay.remy.model.dto.form.UserRegistrationForm;
import org.springframework.stereotype.Component;

@Component
public class UserConvertor {

    public User fromForm(UserRegistrationForm form) {
        return User.builder()
                .login(form.getLogin())
                .password(form.getPassword())
                .build();
    }

    public UserDto toDto(User user) {
        return UserDto.builder()
                .login(user.getLogin())
                .password(user.getPassword())
                .build();
    }
}
