package com.yevay.remy.view.api.controller;

import com.yevay.remy.core.facade.AuthFacade;
import com.yevay.remy.core.facade.UserFacade;
import com.yevay.remy.model.dto.UserDto;
import com.yevay.remy.model.dto.UserSessionDto;
import com.yevay.remy.model.dto.form.UserLoginForm;
import com.yevay.remy.model.dto.form.UserRegistrationForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserFacade userFacade;
    private final AuthFacade authFacade;

    public UserController(UserFacade userFacade, AuthFacade authFacade) {
        this.userFacade = userFacade;
        this.authFacade = authFacade;
    }

    @PostMapping("/register")
    public UserDto registerUser(@RequestBody @Valid UserRegistrationForm userRegistrationForm) {
        return userFacade.create(userRegistrationForm);
    }

    @PostMapping("/login")
    public UserSessionDto login(@RequestBody UserLoginForm form) {
        return authFacade.login(form);
    }
}
