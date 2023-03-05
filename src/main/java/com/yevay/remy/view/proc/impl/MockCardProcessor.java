package com.yevay.remy.view.proc.impl;

import com.yevay.remy.model.dto.box.*;
import com.yevay.remy.model.dto.card.box.request.CreateCardBoxRequest;
import com.yevay.remy.view.proc.BoxProcessor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MockCardProcessor implements BoxProcessor {

    @Override
    public GetBoxPageResponse process(GetBoxPageRequest request) {
        return GetBoxPageResponse.builder()
                .facets(List.of(
                        BoxFacet.builder().id(1).title("Test 1").build(),
                        BoxFacet.builder().id(2).title("Test 2").build(),
                        BoxFacet.builder().id(3).title("Test 3").build()))
                .totalElements(4)
                .totalPages(2)
                .build();
    }

    @Override
    public CreateBoxResponse process(CreateCardBoxRequest request) {
        return null;
    }

    @Override
    public UpdateBoxResponse process(UpdateBoxRequest request) {
        return null;
    }

    @Override
    public DeleteBoxResponse process(DeleteBoxRequest request) {
        return null;
    }
}
