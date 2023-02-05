package com.yevay.remy.view.api.controller;

import com.yevay.remy.core.facade.CardBoxFacade;
import com.yevay.remy.model.dto.card.box.request.GetCardBoxPageableRequest;
import com.yevay.remy.model.dto.card.box.response.CardBoxFacetPageableResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card-box")
public class CardBoxController {

    private final CardBoxFacade cardBoxFacade;

    public CardBoxController(CardBoxFacade cardBoxFacade) {
        this.cardBoxFacade = cardBoxFacade;
    }

    @GetMapping("/pageable")
    public ResponseEntity<?> getPageable(@RequestBody GetCardBoxPageableRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        CardBoxFacetPageableResponse response = cardBoxFacade.getPageable(pageable);
        return ResponseEntity.ok(response);
    }
}
