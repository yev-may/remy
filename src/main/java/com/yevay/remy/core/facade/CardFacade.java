package com.yevay.remy.core.facade;

import com.yevay.remy.model.dto.CardFacetDto;
import com.yevay.remy.model.dto.card.request.GetCardListRequest;
import com.yevay.remy.model.dto.card.response.CardListResponse;
import com.yevay.remy.model.dto.form.CardCreationForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CardFacade {

    CardListResponse getList(GetCardListRequest request);

    Page<CardFacetDto> getFacetsByBoxForCurrentUser(long boxId, Pageable pageable);

    void create(CardCreationForm form);

}
