package com.yevay.remy.model.dto.box;

import lombok.Data;

@Data
public class GetBoxPageRequest {
    private int page;
    private int size;
}
