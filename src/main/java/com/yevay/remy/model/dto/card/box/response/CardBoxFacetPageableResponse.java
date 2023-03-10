package com.yevay.remy.model.dto.card.box.response;

import com.yevay.remy.model.dto.BoxFacetDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardBoxFacetPageableResponse {

    private long totalElements;

    private int totalPages;

    private int currentPage;

    private List<BoxFacetDto> facets;

}
