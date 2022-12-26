package com.yevay.remy.model.dto.error;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldErrorMessage {
    private String field;
    private String message;
}
