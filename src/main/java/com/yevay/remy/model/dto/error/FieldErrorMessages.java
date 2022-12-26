package com.yevay.remy.model.dto.error;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldErrorMessages {
    private Set<FieldErrorMessage> fieldErrors;
}
