package com.yevay.remy.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoxFacetDto {
    private long id;
    private String title;
    private String lastRepeat;
    private int cardsAddedToday;
}
