package com.yevay.remy.model.dto.form;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardCreationForm {
    private long boxId;
    @NotBlank(message="Should not be blank")
    private String question;
    @NotBlank(message="Should not be blank")
    private String answer;
}
