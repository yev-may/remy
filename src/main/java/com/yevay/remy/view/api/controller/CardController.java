package com.yevay.remy.view.api.controller;

import com.yevay.remy.model.dto.card.CreateCardRequest;
import com.yevay.remy.model.dto.card.CreateCardResponse;
import com.yevay.remy.view.proc.CardProcessor;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
@AllArgsConstructor
public class CardController {

    private final CardProcessor cardProcessor;

    @PostMapping("/create")
    public ResponseEntity<CreateCardResponse> createCard(@RequestBody CreateCardRequest request) {
        CreateCardResponse response = cardProcessor.process(request);
        return ResponseEntity.ok(response);
    }
}
