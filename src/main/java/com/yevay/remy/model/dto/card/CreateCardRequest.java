package com.yevay.remy.model.dto.card;

import lombok.Data;

@Data
public class CreateCardRequest {
    private long boxId;
    private String question;
    private String answer;
}
