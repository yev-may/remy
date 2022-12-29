package com.yevay.remy.core.facade.impl;

import com.yevay.remy.core.facade.CardBoxFacade;
import com.yevay.remy.core.service.CardBoxService;
import com.yevay.remy.model.domain.CardBox;
import com.yevay.remy.model.dto.form.CardBoxCreationForm;
import org.springframework.stereotype.Component;

@Component
public class DefaultCardBoxFacade implements CardBoxFacade {

    private final CardBoxService cardBoxService;

    public DefaultCardBoxFacade(CardBoxService cardBoxService) {
        this.cardBoxService = cardBoxService;
    }

    @Override
    public void create(CardBoxCreationForm form) {
        CardBox cardBox = CardBox.builder()
                .title(form.getTitle()).build();
        cardBoxService.save(cardBox);
    }
}
