package com.yevay.remy.model.dto.box;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetBoxPageResponse {
    private List<BoxFacet> facets;
    private long totalElements;
    private int totalPages;
    private int currentPage;
}
