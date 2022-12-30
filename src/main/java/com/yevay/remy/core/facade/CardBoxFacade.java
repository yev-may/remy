package com.yevay.remy.core.facade;

import com.yevay.remy.model.dto.CardBoxFacetDto;
import com.yevay.remy.model.dto.form.CardBoxCreationForm;

import java.util.List;

public interface CardBoxFacade {
    List<CardBoxFacetDto> getAllForCurrentUser();
    void create(CardBoxCreationForm form);
}
