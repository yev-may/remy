package com.yevay.remy.view.proc.impl;

import com.yevay.remy.core.service.CardBoxService;
import com.yevay.remy.core.service.SessionService;
import com.yevay.remy.model.converter.BoxConverter;
import com.yevay.remy.model.domain.Box;
import com.yevay.remy.model.dto.box.*;
import com.yevay.remy.view.proc.BoxProcessor;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DefaultBoxProcessor implements BoxProcessor {

    private final static int DEFAULT_MAX_REPETITION_LEVEL = 4;
    private final static int DEFAULT_REGRESSION_LEVEL = -1;
    private final static int NEVER_REPEATED_BOX_REPETITION_LEVEL = -1;

    private final SessionService sessionService;
    private final CardBoxService cardBoxService;
    private final BoxConverter boxConverter;

    @Override
    public GetBoxPageResponse process(GetBoxPageRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by("title"));
        Page<Box> boxPage = cardBoxService.getByOwner(pageable, sessionService.getCurrentUser());
        return boxConverter.toBoxPageResponse(boxPage);
    }

    @Override
    public CreateBoxResponse process(CreateBoxRequest request) {
        Box box = createBoxToSave(request);
        Box savedBox = cardBoxService.save(box);
        return boxConverter.toCreateBoxResponse(savedBox);
    }

    private Box createBoxToSave(CreateBoxRequest request) {
        return Box.builder()
                .title(request.getTitle())
                .owner(sessionService.getCurrentUser())
                .maxRepetitionLevel(DEFAULT_MAX_REPETITION_LEVEL)
                .regressionLevel(DEFAULT_REGRESSION_LEVEL)
                .lastRepeatedLevel(NEVER_REPEATED_BOX_REPETITION_LEVEL)
                .build();
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
