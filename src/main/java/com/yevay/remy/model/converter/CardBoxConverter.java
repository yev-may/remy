package com.yevay.remy.model.converter;

import com.yevay.remy.model.domain.CardBox;
import com.yevay.remy.model.dto.CardBoxFacetDto;
import com.yevay.remy.model.dto.card.box.response.CardBoxFacetPageableResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CardBoxConverter {

    public CardBoxFacetPageableResponse toPageableFacetResponse(Page<CardBox> cardBoxPage) {
        Page<CardBoxFacetDto> cardBoxFacetPage = toFacetPage(cardBoxPage);
        return CardBoxFacetPageableResponse.builder()
                .totalElements(cardBoxFacetPage.getTotalElements())
                .totalPages(cardBoxFacetPage.getTotalPages())
                .facets(cardBoxFacetPage.getContent()).build();
    }

    public Page<CardBoxFacetDto> toFacetPage(Page<CardBox> cardBoxPage) {
        List<CardBoxFacetDto> cardBoxFacets = cardBoxPage.getContent().stream()
                .map(this::toFacet)
                .collect(Collectors.toList());
        return new PageImpl<>(cardBoxFacets, cardBoxPage.getPageable(), cardBoxPage.getTotalElements());
    }

    private CardBoxFacetDto toFacet(CardBox cardBox) {
        return CardBoxFacetDto.builder()
                .id(cardBox.getId())
                .title(cardBox.getTitle())
                .cardsAddedToday(1)
                .lastRepeat("Yesterday").build();
    }
}
