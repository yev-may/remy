package com.yevay.remy.model.dto.card.response;

import com.yevay.remy.model.dto.CardDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardListResponse {
    private List<CardDto> cards;
}
