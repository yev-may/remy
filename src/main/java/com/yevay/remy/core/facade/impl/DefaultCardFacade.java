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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public Page<CardFacetDto> getFacetsByBoxForCurrentUser(long boxId, Pageable pageable) {
        return getFacetsByBoxAndOwner(boxId, sessionService.getCurrentUser(), pageable);
    }

    private Page<CardFacetDto> getFacetsByBoxAndOwner(long boxId, User owner, Pageable pageable) {
        CardBox cardBox = cardBoxService.getByIdAndOwner(boxId, owner);
        Page<Card> cardPage = cardService.getByBox(cardBox, pageable);
        List<CardFacetDto> cardFacets = toFacetDto(cardPage.get());
        return new PageImpl<>(cardFacets, pageable, cardPage.getTotalElements());
    }

    private List<CardFacetDto> toFacetDto(Stream<Card> cardStream) {
        return cardStream.map(this::toFacetDto).collect(Collectors.toList());
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
