package com.yevay.remy.model.converter;

import com.yevay.remy.model.domain.Card;
import com.yevay.remy.model.dto.CardDto;
import com.yevay.remy.model.dto.card.response.CardListResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CardConverter {

    public CardListResponse toCardListResponse(List<Card> cards) {
        List<CardDto> cardDtoList = cards.stream()
                .map(this::toDto)
                .toList();
        return CardListResponse.builder()
                .cards(cardDtoList).build();
    }

    public CardDto toDto(Card card) {
        return CardDto.builder()
                .id(card.getId())
                .question(card.getQuestion())
                .answer(card.getAnswer()).build();
    }
}
