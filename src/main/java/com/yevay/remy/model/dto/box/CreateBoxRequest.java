package com.yevay.remy.model.dto.box;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class CreateBoxRequest {
    @Size(min=1, max=16, message="Length must be between 1 and 16 characters")
    private String title;
}
