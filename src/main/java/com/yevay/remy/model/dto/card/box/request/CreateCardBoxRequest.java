package com.yevay.remy.model.dto.card.box.request;

import lombok.*;

import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCardBoxRequest {
    @Size(min=1, max=16, message="Length must be between 1 and 16 characters")
    private String title;
}
