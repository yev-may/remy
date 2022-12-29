package com.yevay.remy.core.service.impl;

import com.yevay.remy.core.service.CardBoxService;
import com.yevay.remy.model.domain.CardBox;
import com.yevay.remy.model.domain.User;
import com.yevay.remy.model.repo.CardBoxRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultCardBoxService implements CardBoxService {

    private final CardBoxRepository cardBoxRepository;

    public DefaultCardBoxService(CardBoxRepository cardBoxRepository) {
        this.cardBoxRepository = cardBoxRepository;
    }

    @Override
    public List<CardBox> getAllForUser(User user) {
        return cardBoxRepository.findAllByOwner(user);
    }

    @Override
    public void save(CardBox cardBox) {
        cardBoxRepository.save(cardBox);
    }
}
