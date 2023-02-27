package com.yevay.remy.view.proc.impl;

import com.yevay.remy.core.service.UserService;
import com.yevay.remy.model.converter.UserConvertor;
import com.yevay.remy.model.domain.User;
import com.yevay.remy.model.dto.user.UserRegistrationRequest;
import com.yevay.remy.model.dto.user.UserRegistrationResponse;
import com.yevay.remy.view.proc.UserProcessor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DefaultUserProcessor implements UserProcessor {

    private final UserService userService;
    private final UserConvertor userConvertor;

    @Override
    public UserRegistrationResponse process(UserRegistrationRequest request) {
        User user = userConvertor.fromRequest(request);
        User savedUser = userService.save(user);
        return userConvertor.toResponse(savedUser);
    }
}
