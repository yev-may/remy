package com.yevay.remy.core.facade.impl;

import com.yevay.remy.core.facade.CardFacade;
import com.yevay.remy.core.service.CardService;
import com.yevay.remy.model.domain.Card;
import com.yevay.remy.model.domain.CardBox;
import com.yevay.remy.model.dto.form.CardCreationForm;
import org.springframework.stereotype.Service;

@Service
public class DefaultCardFacade implements CardFacade {

    private final CardService cardService;

    public DefaultCardFacade(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public void create(CardCreationForm form) {
        Card card = Card.builder()
                .box(CardBox.builder()
                        .id(form.getBoxId()).build())
                .question(form.getQuestion())
                .answer(form.getAnswer()).build();
        cardService.save(card);
    }
}
