package com.yevay.remy.model.converter;

import com.yevay.remy.model.domain.Box;
import com.yevay.remy.model.dto.box.BoxFacet;
import com.yevay.remy.model.dto.box.GetBoxPageResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BoxConverter {

    public GetBoxPageResponse toBoxPageResponse(Page<Box> boxPage) {
        List<BoxFacet> boxFacets = toFacets(boxPage.getContent());
        return GetBoxPageResponse.builder()
                .facets(boxFacets)
                .totalElements(boxPage.getTotalElements())
                .totalPages(boxPage.getTotalPages())
                .currentPage(boxPage.getNumber())
                .build();
    }

    private List<BoxFacet> toFacets(List<Box> boxes) {
        return boxes.stream().map(this::toFacet).toList();
    }

    private BoxFacet toFacet(Box box) {
        return BoxFacet.builder()
                .id(box.getId())
                .title(box.getTitle())
                .build();
    }
}
