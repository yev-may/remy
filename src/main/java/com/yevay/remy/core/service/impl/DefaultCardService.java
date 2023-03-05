package com.yevay.remy.core.service.impl;

import com.yevay.remy.core.repo.CardRepository;
import com.yevay.remy.core.service.CardService;
import com.yevay.remy.model.domain.Card;
import org.springframework.stereotype.Service;

@Service
public class DefaultCardService implements CardService {

    private final CardRepository cardRepository;

    public DefaultCardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public Card save(Card card) {
        return cardRepository.save(card);
    }
}
