package com.yevay.remy.core.service.impl;

import com.yevay.remy.core.service.CardService;
import com.yevay.remy.model.domain.Card;
import com.yevay.remy.model.domain.CardBox;
import com.yevay.remy.model.repo.CardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DefaultCardService implements CardService {

    private final CardRepository cardRepository;

    public DefaultCardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public Page<Card> getByBox(CardBox box, Pageable pageable) {
        return cardRepository.findByBox(box, pageable);
    }

    @Override
    public void save(Card card) {
        cardRepository.save(card);
    }
}
