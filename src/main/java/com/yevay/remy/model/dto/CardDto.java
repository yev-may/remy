package com.yevay.remy.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    private String question;
    private String answer;
}
