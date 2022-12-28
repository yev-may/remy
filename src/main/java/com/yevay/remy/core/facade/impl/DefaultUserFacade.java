package com.yevay.remy.core.facade.impl;

import com.yevay.remy.core.facade.UserFacade;
import com.yevay.remy.core.service.UserService;
import com.yevay.remy.model.converter.UserConvertor;
import com.yevay.remy.model.domain.User;
import com.yevay.remy.model.dto.form.UserRegistrationForm;
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
    public void register(UserRegistrationForm userRegistrationForm) {
        User user = userConvertor.fromForm(userRegistrationForm);
        userService.save(user);
    }
}
