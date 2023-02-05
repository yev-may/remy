package com.yevay.remy.core.facade;

import com.yevay.remy.model.dto.CardBoxDto;
import com.yevay.remy.model.dto.CardBoxFacetDto;
import com.yevay.remy.model.dto.card.box.request.CreateCardBoxRequest;
import com.yevay.remy.model.dto.card.box.request.DeleteCardBoxRequest;
import com.yevay.remy.model.dto.card.box.request.UpdateCardBoxRequest;
import com.yevay.remy.model.dto.card.box.response.CardBoxFacetPageableResponse;
import com.yevay.remy.model.dto.form.CardBoxCreationForm;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CardBoxFacade {

    CardBoxFacetPageableResponse getPageable(Pageable pageable);

    void create(CreateCardBoxRequest request);

    CardBoxFacetDto update(UpdateCardBoxRequest request);

    void delete(DeleteCardBoxRequest request);

    List<CardBoxFacetDto> getAllForCurrentUser();

    CardBoxDto getByIdForCurrentUser(long id);

    void create(CardBoxCreationForm form);

}
