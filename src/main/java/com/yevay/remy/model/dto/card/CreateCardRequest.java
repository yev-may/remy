package com.yevay.remy.model.dto.card;

import lombok.Data;

@Data
public class CreateCardRequest {
    private String boxId;
    private String question;
    private String answer;
}
