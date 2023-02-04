package com.yevay.remy.view.api.controller;

import com.yevay.remy.core.facade.UserFacade;
import com.yevay.remy.model.dto.form.UserRegistrationForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegistrationForm form) {
        userFacade.register(form);
        return ResponseEntity.ok("Success!");
    }
}
