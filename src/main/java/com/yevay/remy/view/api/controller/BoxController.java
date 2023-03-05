package com.yevay.remy.view.api.controller;

import com.yevay.remy.core.facade.BoxFacade;
import com.yevay.remy.model.dto.BoxFacetDto;
import com.yevay.remy.model.dto.box.CreateBoxRequest;
import com.yevay.remy.model.dto.box.CreateBoxResponse;
import com.yevay.remy.model.dto.box.GetBoxPageRequest;
import com.yevay.remy.model.dto.box.GetBoxPageResponse;
import com.yevay.remy.model.dto.card.box.request.DeleteCardBoxRequest;
import com.yevay.remy.model.dto.card.box.request.UpdateCardBoxRequest;
import com.yevay.remy.view.proc.BoxProcessor;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/box")
@AllArgsConstructor
public class BoxController {

    private final BoxFacade boxFacade;
    private final BoxProcessor boxProcessor;

    @PostMapping("/pageable")
    public ResponseEntity<?> getPageable(@RequestBody GetBoxPageRequest request) {
        GetBoxPageResponse response = boxProcessor.process(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCardBox(@Valid @RequestBody CreateBoxRequest request) {
        CreateBoxResponse response = boxProcessor.process(request);
        return ResponseEntity.ok(response);
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
