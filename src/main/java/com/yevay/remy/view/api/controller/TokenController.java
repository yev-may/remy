package com.yevay.remy.view.api.controller;

import com.yevay.remy.model.dto.token.TokenRequest;
import com.yevay.remy.model.dto.token.TokenResponse;
import com.yevay.remy.view.proc.TokenProcessor;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
@AllArgsConstructor
public class TokenController {

    private final TokenProcessor tokenProcessor;

    @PostMapping("/get")
    public ResponseEntity<TokenResponse> getJwtToken(@RequestBody TokenRequest request) {
        TokenResponse response = tokenProcessor.process(request);
        return ResponseEntity.ok(response);
    }
}
