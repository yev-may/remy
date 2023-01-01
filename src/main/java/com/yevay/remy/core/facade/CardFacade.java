package com.yevay.remy.core.facade;

import com.yevay.remy.model.dto.CardFacetDto;
import com.yevay.remy.model.dto.form.CardCreationForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CardFacade {
    Page<CardFacetDto> getFacetsByBoxForCurrentUser(long boxId, Pageable pageable);
    void create(CardCreationForm form);
}
