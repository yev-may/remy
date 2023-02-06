package com.yevay.remy.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    private long id;
    private String question;
    private String answer;
}
