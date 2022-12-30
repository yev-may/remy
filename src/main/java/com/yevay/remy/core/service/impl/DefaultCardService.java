package com.yevay.remy.core.service.impl;

import com.yevay.remy.core.service.CardService;
import com.yevay.remy.model.domain.Card;
import com.yevay.remy.model.domain.CardBox;
import com.yevay.remy.model.repo.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultCardService implements CardService {

    private final CardRepository cardRepository;

    public DefaultCardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public List<Card> getByBox(CardBox box) {
        return cardRepository.findByBox(box);
    }

    @Override
    public void save(Card card) {
        cardRepository.save(card);
    }
}
