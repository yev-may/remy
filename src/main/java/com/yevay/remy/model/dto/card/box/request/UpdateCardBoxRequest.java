package com.yevay.remy.model.dto.card.box.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCardBoxRequest {
    private long id;
    private CreateCardBoxRequest body;
}
