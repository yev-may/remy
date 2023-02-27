package com.yevay.remy.view.api.controller;

import com.yevay.remy.model.dto.jwt.JwtRequest;
import com.yevay.remy.model.dto.jwt.JwtResponse;
import com.yevay.remy.view.proc.JwtAuthProcessor;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
@AllArgsConstructor
public class JwtTokenController {

    private final JwtAuthProcessor jwtAuthProcessor;

    @PostMapping("/new")
    public ResponseEntity<JwtResponse> getJwtToken(@RequestBody JwtRequest request) {
        JwtResponse response = jwtAuthProcessor.process(request);
        return ResponseEntity.ok(response);
    }
}
