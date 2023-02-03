package com.yevay.remy.view.api.controller;

import com.yevay.remy.core.facade.JwtAuthFacade;
import com.yevay.remy.model.dto.jwt.JwtRequest;
import com.yevay.remy.model.dto.jwt.JwtResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class JwtAuthController {

    private final JwtAuthFacade jwtAuthFacade;

    public JwtAuthController(JwtAuthFacade jwtAuthFacade) {
        this.jwtAuthFacade = jwtAuthFacade;
    }

    @PostMapping("/auth")
    public ResponseEntity<JwtResponse> createAuthToken(@RequestBody JwtRequest request) {
        JwtResponse response = jwtAuthFacade.createAuthToken(request);
        return ResponseEntity.ok(response);
    }
}
