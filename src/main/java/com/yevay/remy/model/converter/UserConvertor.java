package com.yevay.remy.model.converter;

import com.yevay.remy.model.domain.User;
import com.yevay.remy.model.dto.user.UserRegistrationRequest;
import com.yevay.remy.model.dto.user.UserRegistrationResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserConvertor {

    private final PasswordEncoder passwordEncoder;

    public UserConvertor(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User fromRequest(UserRegistrationRequest request) {
        return User.builder()
                .email(request.getEmail())
                .login(request.getLogin())
                .password(passwordEncoder.encode(request.getPassword())).build();
    }

    public UserRegistrationResponse toResponse(User user) {
        return UserRegistrationResponse.builder()
                .login(user.getLogin()).build();
    }
}
