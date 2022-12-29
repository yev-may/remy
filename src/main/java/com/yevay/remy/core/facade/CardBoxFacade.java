package com.yevay.remy.core.facade;

import com.yevay.remy.model.dto.CardBoxDto;
import com.yevay.remy.model.dto.form.CardBoxCreationForm;

import java.util.List;

public interface CardBoxFacade {
    List<CardBoxDto> getAllForCurrentUser();
    void create(CardBoxCreationForm form);
}
