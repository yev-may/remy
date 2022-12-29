package com.yevay.remy.core.service.impl;

import com.yevay.remy.core.service.CardBoxService;
import com.yevay.remy.model.domain.CardBox;
import com.yevay.remy.model.repo.CardBoxRepository;
import org.springframework.stereotype.Service;

@Service
public class DefaultCardBoxService implements CardBoxService {

    private final CardBoxRepository cardBoxRepository;

    public DefaultCardBoxService(CardBoxRepository cardBoxRepository) {
        this.cardBoxRepository = cardBoxRepository;
    }

    @Override
    public void save(CardBox cardBox) {
        cardBoxRepository.save(cardBox);
    }
}
