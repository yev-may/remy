package com.yevay.remy.core.facade.impl;

import com.yevay.remy.core.facade.CardFacade;
import com.yevay.remy.core.service.CardBoxService;
import com.yevay.remy.core.service.CardService;
import com.yevay.remy.core.service.SessionService;
import com.yevay.remy.model.domain.Card;
import com.yevay.remy.model.domain.CardBox;
import com.yevay.remy.model.domain.User;
import com.yevay.remy.model.dto.CardFacetDto;
import com.yevay.remy.model.dto.form.CardCreationForm;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultCardFacade implements CardFacade {

    private final static int SHORT_QUESTION_LENGTH = 20;

    private final CardService cardService;
    private final CardBoxService cardBoxService;
    private final SessionService sessionService;

    public DefaultCardFacade(CardService cardService, CardBoxService cardBoxService, SessionService sessionService) {
        this.cardService = cardService;
        this.cardBoxService = cardBoxService;
        this.sessionService = sessionService;
    }

    @Override
    public List<CardFacetDto> getFacetsByBoxForCurrentUser(long boxId) {
        return getFacetsByBoxAndOwner(boxId, sessionService.getCurrentUser());
    }

    private List<CardFacetDto> getFacetsByBoxAndOwner(long boxId, User owner) {
        CardBox cardBox = cardBoxService.getByIdAndOwner(boxId, owner);
        return cardService.getByBox(cardBox).stream()
                .map(this::toFacetDto)
                .collect(Collectors.toList());
    }

    private CardFacetDto toFacetDto(Card card) {
        return CardFacetDto.builder()
                .id(card.getId())
                .shortQuestion(getShortQuestion(card)).build();
    }

    private String getShortQuestion(Card card) {
        String question = card.getQuestion();
        return question.length() > SHORT_QUESTION_LENGTH
                ? question.substring(0, SHORT_QUESTION_LENGTH)
                : question;
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
