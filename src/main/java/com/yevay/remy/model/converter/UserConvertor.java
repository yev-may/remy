package com.yevay.remy.model.converter;

import com.yevay.remy.model.domain.User;
import com.yevay.remy.model.dto.UserDto;
import com.yevay.remy.model.dto.form.UserRegistrationForm;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserConvertor {

    private final PasswordEncoder passwordEncoder;

    public UserConvertor(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User fromForm(UserRegistrationForm form) {
        return User.builder()
                .email(form.getEmail())
                .login(form.getLogin())
                .password(passwordEncoder.encode(form.getPassword())).build();
    }

    public UserDto toDto(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .login(user.getLogin()).build();
    }
}
