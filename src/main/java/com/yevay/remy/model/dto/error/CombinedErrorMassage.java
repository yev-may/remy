package com.yevay.remy.model.dto.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static org.springframework.util.CollectionUtils.isEmpty;

@Getter
@Setter
public class CombinedErrorMassage {
    @JsonInclude(NON_NULL)
    private ErrorMessages errorMessages;
    @JsonInclude(NON_NULL)
    private FieldErrorMessages fieldErrorMessages;

    public static CombinedErrorMassage builder() {
        return new CombinedErrorMassage();
    }

    public CombinedErrorMassage messages(ErrorMessages messages) {
        if (isEmpty(messages.getMessages())) return this;
        this.errorMessages = messages;
        return this;
    }

    public CombinedErrorMassage fieldMessages(FieldErrorMessages messages) {
        if (isEmpty(messages.getFieldErrors())) return this;
        this.fieldErrorMessages = messages;
        return this;
    }
}
