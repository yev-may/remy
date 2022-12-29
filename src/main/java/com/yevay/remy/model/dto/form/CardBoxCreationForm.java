package com.yevay.remy.model.dto.form;

import lombok.*;

import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardBoxCreationForm {
    @Size(min=1, max=16, message="Length must be between 1 and 16 characters")
    private String title;
    private Long workspaceId;
}
