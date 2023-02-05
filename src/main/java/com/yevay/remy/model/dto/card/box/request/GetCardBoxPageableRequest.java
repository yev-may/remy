package com.yevay.remy.model.dto.card.box.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetCardBoxPageableRequest {
    private int page;
    private int size;
}
