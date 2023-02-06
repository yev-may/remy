package com.yevay.remy.model.dto.card.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCardListRequest {
    private long boxId;
    private int level;
}
