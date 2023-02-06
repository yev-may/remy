package com.yevay.remy.model.dto.card.box.response;

import com.yevay.remy.model.dto.CardDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardBoxResponse {
    private long id;
    private String title;
    private int levels;
    private List<CardsOnLevel> cardsOnLevels;

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    private static class CardsOnLevel {
        private int level;
        private List<CardDto> cards;
    }
}
