package com.yevay.remy.core.facade;

import com.yevay.remy.model.dto.CardFacetDto;
import com.yevay.remy.model.dto.form.CardCreationForm;

import java.util.List;

public interface CardFacade {
    List<CardFacetDto> getFacetsByBoxForCurrentUser(long boxId);
    void create(CardCreationForm form);
}
