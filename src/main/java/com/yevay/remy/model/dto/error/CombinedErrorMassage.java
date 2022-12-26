package com.yevay.remy.model.dto.error;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CombinedErrorMassage {
    private ErrorMessages errorMessages;
    private FieldErrorMessages fieldErrorMessages;
}
