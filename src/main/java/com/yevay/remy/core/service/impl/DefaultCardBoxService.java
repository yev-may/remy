package com.yevay.remy.core.service.impl;

import com.yevay.remy.core.service.CardBoxService;
import com.yevay.remy.model.domain.Card;
import com.yevay.remy.model.domain.CardBox;
import com.yevay.remy.model.domain.User;
import com.yevay.remy.model.repo.CardBoxRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultCardBoxService implements CardBoxService {

    private final CardBoxRepository cardBoxRepository;

    public DefaultCardBoxService(CardBoxRepository cardBoxRepository) {
        this.cardBoxRepository = cardBoxRepository;
    }

    @Override
    public Page<CardBox> getByOwner(Pageable pageable, User owner) {
        return cardBoxRepository.findByOwner(pageable, owner);
    }

    @Override
    public List<CardBox> getAllByOwner(User owner) {
        return cardBoxRepository.findAllByOwner(owner);
    }

    @Override
    public CardBox getByIdAndOwner(long id, User owner) {
        return cardBoxRepository.findByIdAndOwner(id, owner);
    }

    @Override
    public CardBox save(CardBox cardBox) {
        return cardBoxRepository.save(cardBox);
    }

    @Override
    public void delete(long cardBoxId) {
        cardBoxRepository.deleteById(cardBoxId);
    }
}
