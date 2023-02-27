package com.yevay.remy.view.proc.impl;

import com.yevay.remy.model.dto.box.*;
import com.yevay.remy.model.dto.card.box.request.CreateCardBoxRequest;
import com.yevay.remy.view.proc.BoxProcessor;
import org.springframework.stereotype.Component;

@Component
public class MockCardProcessor implements BoxProcessor {

    @Override
    public GetBoxPageResponse process(GetBoxPageRequest request) {
        return null;
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
