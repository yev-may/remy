package com.yevay.remy.view.api.controller;

import com.yevay.remy.model.dto.user.UserRegistrationRequest;
import com.yevay.remy.model.dto.user.UserRegistrationResponse;
import com.yevay.remy.view.proc.UserProcessor;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserProcessor userProcessor;

    @PostMapping("/register")
    public ResponseEntity<UserRegistrationResponse> registerUser(@Valid @RequestBody UserRegistrationRequest request) {
        UserRegistrationResponse response = userProcessor.process(request);
        return ResponseEntity.ok(response);
    }
}
