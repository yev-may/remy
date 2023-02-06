package com.yevay.remy.view.api.controller;

import com.yevay.remy.core.facade.CardFacade;
import com.yevay.remy.model.dto.card.request.GetCardListRequest;
import com.yevay.remy.model.dto.card.response.CardListResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

    private final CardFacade cardFacade;

    public CardController(CardFacade cardFacade) {
        this.cardFacade = cardFacade;
    }

    @GetMapping("/get-list")
    public ResponseEntity<?> getCardList(GetCardListRequest request) {
        CardListResponse response = cardFacade.getList(request);
        return ResponseEntity.ok(response);
    }
}
