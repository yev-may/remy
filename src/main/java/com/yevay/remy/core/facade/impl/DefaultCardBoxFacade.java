package com.yevay.remy.core.facade.impl;

import com.yevay.remy.core.facade.CardBoxFacade;
import com.yevay.remy.core.service.CardBoxService;
import com.yevay.remy.core.service.SessionService;
import com.yevay.remy.model.converter.CardBoxConverter;
import com.yevay.remy.model.domain.CardBox;
import com.yevay.remy.model.domain.User;
import com.yevay.remy.model.dto.CardBoxDto;
import com.yevay.remy.model.dto.CardBoxFacetDto;
import com.yevay.remy.model.dto.card.box.request.CreateCardBoxRequest;
import com.yevay.remy.model.dto.card.box.request.DeleteCardBoxRequest;
import com.yevay.remy.model.dto.card.box.request.UpdateCardBoxRequest;
import com.yevay.remy.model.dto.card.box.response.CardBoxFacetPageableResponse;
import com.yevay.remy.model.dto.form.CardBoxCreationForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DefaultCardBoxFacade implements CardBoxFacade {

    private final CardBoxService cardBoxService;
    private final SessionService sessionService;
    private final CardBoxConverter cardBoxConverter;

    public DefaultCardBoxFacade(CardBoxService cardBoxService, SessionService sessionService, CardBoxConverter cardBoxConverter) {
        this.cardBoxService = cardBoxService;
        this.sessionService = sessionService;
        this.cardBoxConverter = cardBoxConverter;
    }

    @Override
    public CardBoxFacetPageableResponse getPageable(Pageable pageable) {
        Page<CardBox> cardBoxPage = cardBoxService.getByOwner(pageable, sessionService.getCurrentUser());
        return cardBoxConverter.toPageableFacetResponse(cardBoxPage);
    }

    @Override
    public void create(CreateCardBoxRequest request) {
        CardBox cardBox = CardBox.builder()
                .title(request.getTitle())
                .owner(sessionService.getCurrentUser()).build();
        cardBoxService.save(cardBox);
    }

    @Override
    public CardBoxFacetDto update(UpdateCardBoxRequest request) {
        CardBox cardBox = CardBox.builder()
                .id(request.getId())
                .title(request.getBody().getTitle())
                .owner(sessionService.getCurrentUser()).build();
        CardBox updatedCardBox = cardBoxService.save(cardBox);
        return cardBoxConverter.toFacet(updatedCardBox);
    }

    @Override
    public void delete(DeleteCardBoxRequest request) {
        cardBoxService.delete(request.getId());
    }

    @Override
    public List<CardBoxFacetDto> getAllForCurrentUser() {
        return getAllByOwner(sessionService.getCurrentUser());
    }

    private List<CardBoxFacetDto> getAllByOwner(User user) {
        return cardBoxService.getAllByOwner(user).stream()
                .map(this::toFacetDto)
                .collect(Collectors.toList());
    }

    private CardBoxFacetDto toFacetDto(CardBox cardBox) {
        return CardBoxFacetDto.builder()
                .id(cardBox.getId())
                .title(cardBox.getTitle())
                .lastRepeat("1 day age")
                .cardsAddedToday(1).build();
    }

    @Override
    public CardBoxDto getByIdForCurrentUser(long id) {
        return getByIdAndOwner(id, sessionService.getCurrentUser());
    }

    private CardBoxDto getByIdAndOwner(long id, User owner) {
        return Optional.ofNullable(cardBoxService.getByIdAndOwner(id, owner))
                .map(this::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Card box with id [" + id + "] for and owner [" + owner.getLogin() +"] not found"));
    }

    private CardBoxDto toDto(CardBox cardBox) {
        return CardBoxDto.builder()
                .id(cardBox.getId())
                .title(cardBox.getTitle()).build();
    }

    @Override
    public void create(CardBoxCreationForm form) {
        CardBox cardBox = CardBox.builder()
                .title(form.getTitle())
                .owner(sessionService.getCurrentUser()).build();
        cardBoxService.save(cardBox);
    }
}
