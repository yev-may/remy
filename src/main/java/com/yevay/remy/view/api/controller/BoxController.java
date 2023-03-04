package com.yevay.remy.view.api.controller;

import com.yevay.remy.core.facade.BoxFacade;
import com.yevay.remy.model.dto.BoxFacetDto;
import com.yevay.remy.model.dto.card.box.request.CreateCardBoxRequest;
import com.yevay.remy.model.dto.card.box.request.DeleteCardBoxRequest;
import com.yevay.remy.model.dto.card.box.request.GetCardBoxPageableRequest;
import com.yevay.remy.model.dto.card.box.request.UpdateCardBoxRequest;
import com.yevay.remy.model.dto.card.box.response.CardBoxFacetPageableResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/box")
public class BoxController {

    private final BoxFacade boxFacade;

    public BoxController(BoxFacade boxFacade) {
        this.boxFacade = boxFacade;
    }

    @PostMapping("/pageable")
    public ResponseEntity<?> getPageable(@RequestBody GetCardBoxPageableRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        CardBoxFacetPageableResponse response = boxFacade.getPageable(pageable);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCardBox(@Valid @RequestBody CreateCardBoxRequest request) {
        boxFacade.create(request);
        return ResponseEntity.ok("Card box created!");
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateCardBox(@Valid @RequestBody UpdateCardBoxRequest request) {
        BoxFacetDto updatedCardBox = boxFacade.update(request);
        return ResponseEntity.ok(updatedCardBox);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteCardBox(@RequestBody DeleteCardBoxRequest request) {
        boxFacade.delete(request);
        return ResponseEntity.ok("Card box deleted");
    }
}
