package com.yevay.remy.core.service.impl;

import com.yevay.remy.core.service.CardService;
import com.yevay.remy.model.domain.Box;
import com.yevay.remy.model.domain.Card;
import com.yevay.remy.core.repo.CardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultCardService implements CardService {

    private final CardRepository cardRepository;

    public DefaultCardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public List<Card> getByBoxIdAndLevel(long boxId, int level) {
        return cardRepository.findByBoxIdAndRepeatLevel(boxId, level);
    }

    @Override
    public Page<Card> getByBox(Box box, Pageable pageable) {
        return cardRepository.findByBox(box, pageable);
    }

    @Override
    public void save(Card card) {
        cardRepository.save(card);
    }
}
