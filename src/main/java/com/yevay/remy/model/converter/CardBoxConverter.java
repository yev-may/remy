package com.yevay.remy.model.converter;

import com.yevay.remy.model.domain.Box;
import com.yevay.remy.model.dto.BoxFacetDto;
import com.yevay.remy.model.dto.card.box.response.CardBoxFacetPageableResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CardBoxConverter {

    public CardBoxFacetPageableResponse toPageableFacetResponse(Page<Box> cardBoxPage) {
        Page<BoxFacetDto> cardBoxFacetPage = toFacetPage(cardBoxPage);
        return CardBoxFacetPageableResponse.builder()
                .totalElements(cardBoxFacetPage.getTotalElements())
                .totalPages(cardBoxFacetPage.getTotalPages())
                .currentPage(cardBoxFacetPage.getNumber())
                .facets(cardBoxFacetPage.getContent()).build();
    }

    public Page<BoxFacetDto> toFacetPage(Page<Box> cardBoxPage) {
        List<BoxFacetDto> cardBoxFacets = cardBoxPage.getContent().stream()
                .map(this::toFacet)
                .collect(Collectors.toList());
        return new PageImpl<>(cardBoxFacets, cardBoxPage.getPageable(), cardBoxPage.getTotalElements());
    }

    public BoxFacetDto toFacet(Box box) {
        return BoxFacetDto.builder()
                .id(box.getId())
                .title(box.getTitle())
                .cardsAddedToday(1)
                .lastRepeat("Yesterday").build();
    }
}
