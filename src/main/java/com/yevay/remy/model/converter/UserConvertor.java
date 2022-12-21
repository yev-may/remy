package com.yevay.remy.model.converter;

import com.yevay.remy.model.domain.User;
import com.yevay.remy.model.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserConvertor {

    public User fromDto(UserDto userDto) {
        return User.builder()
                .login(userDto.getLogin())
                .password(userDto.getPassword())
                .build();
    }

    public UserDto toDto(User user) {
        return UserDto.builder()
                .login(user.getLogin())
                .password(user.getPassword())
                .build();
    }
}
