package com.yevay.remy.core.facade.impl;

import com.yevay.remy.core.facade.CardBoxFacade;
import com.yevay.remy.core.service.CardBoxService;
import com.yevay.remy.core.service.SessionService;
import com.yevay.remy.model.domain.CardBox;
import com.yevay.remy.model.domain.User;
import com.yevay.remy.model.dto.CardBoxFacetDto;
import com.yevay.remy.model.dto.form.CardBoxCreationForm;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DefaultCardBoxFacade implements CardBoxFacade {

    private final CardBoxService cardBoxService;
    private final SessionService sessionService;

    public DefaultCardBoxFacade(CardBoxService cardBoxService, SessionService sessionService) {
        this.cardBoxService = cardBoxService;
        this.sessionService = sessionService;
    }

    @Override
    public List<CardBoxFacetDto> getAllForCurrentUser() {
        return getAllForUser(sessionService.getCurrentUser());
    }

    private List<CardBoxFacetDto> getAllForUser(User user) {
        return cardBoxService.getAllForUser(user).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private CardBoxFacetDto toDto(CardBox cardBox) {
        return CardBoxFacetDto.builder()
                .id(cardBox.getId())
                .title(cardBox.getTitle())
                .lastRepeat("1 day age")
                .cardsAddedToday(1).build();
    }

    @Override
    public void create(CardBoxCreationForm form) {
        CardBox cardBox = CardBox.builder()
                .title(form.getTitle())
                .owner(sessionService.getCurrentUser()).build();
        cardBoxService.save(cardBox);
    }
}
