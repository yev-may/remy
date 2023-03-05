package com.yevay.remy.model.dto.box;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateBoxResponse {
    BoxFacet createdBox;
}
