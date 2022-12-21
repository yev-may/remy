package com.yevay.remy.core.facade.impl;

import com.yevay.remy.core.facade.UserFacade;
import com.yevay.remy.core.service.UserService;
import com.yevay.remy.model.converter.UserConvertor;
import com.yevay.remy.model.domain.User;
import com.yevay.remy.model.dto.UserDto;
import org.springframework.stereotype.Component;

@Component("UserFacade")
public class DefaultUserFacade implements UserFacade {

    private final UserService userService;
    private final UserConvertor userConvertor;

    public DefaultUserFacade(UserService userService, UserConvertor userConvertor) {
        this.userService = userService;
        this.userConvertor = userConvertor;
    }

    @Override
    public UserDto create(UserDto userDto) {
        User user = userConvertor.fromDto(userDto);
        User createdUser = userService.save(user);
        return userConvertor.toDto(createdUser);
    }
}
