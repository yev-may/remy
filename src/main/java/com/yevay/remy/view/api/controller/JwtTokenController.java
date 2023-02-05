package com.yevay.remy.view.api.controller;

import com.yevay.remy.core.facade.JwtTokenFacade;
import com.yevay.remy.model.dto.jwt.JwtRequest;
import com.yevay.remy.model.dto.jwt.JwtResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/token")
public class JwtTokenController {

    private final JwtTokenFacade jwtTokenFacade;

    public JwtTokenController(JwtTokenFacade jwtTokenFacade) {
        this.jwtTokenFacade = jwtTokenFacade;
    }

    @PostMapping("/new")
    public ResponseEntity<JwtResponse> getJwtToken(@RequestBody JwtRequest request) {
        JwtResponse response = jwtTokenFacade.getToken(request);
        return ResponseEntity.ok(response);
    }
}
