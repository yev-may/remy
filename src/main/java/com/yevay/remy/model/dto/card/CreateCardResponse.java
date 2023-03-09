package com.yevay.remy.model.dto.card;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateCardResponse {
    private String boxId;
    private String cardId;
    private String question;
    private String answer;
}
