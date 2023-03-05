package com.yevay.remy.view.proc.impl;

import com.yevay.remy.core.service.CardService;
import com.yevay.remy.core.service.SessionService;
import com.yevay.remy.model.domain.Box;
import com.yevay.remy.model.domain.Card;
import com.yevay.remy.model.dto.card.CreateCardRequest;
import com.yevay.remy.model.dto.card.CreateCardResponse;
import com.yevay.remy.view.proc.CardProcessor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DefaultCardProcessor implements CardProcessor {

    private final SessionService sessionService;
    private final CardService cardService;

    @Override
    public CreateCardResponse process(CreateCardRequest request) {
        Card card = createCardToSave(request);
        cardService.save(card);
        return new CreateCardResponse();
    }

    private Card createCardToSave(CreateCardRequest request) {
        return Card.builder()
                .box(Box.builder()
                        .id(request.getBoxId())
                        .owner(sessionService.getCurrentUser())
                        .build())
                .question(request.getQuestion())
                .answer(request.getAnswer())
                .build();
    }
}
