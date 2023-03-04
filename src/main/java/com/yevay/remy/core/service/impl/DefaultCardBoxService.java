package com.yevay.remy.core.service.impl;

import com.yevay.remy.core.service.CardBoxService;
import com.yevay.remy.model.domain.Box;
import com.yevay.remy.model.domain.User;
import com.yevay.remy.core.repo.CardBoxRepository;
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
    public Page<Box> getByOwner(Pageable pageable, User owner) {
        return cardBoxRepository.findByOwner(pageable, owner);
    }

    @Override
    public List<Box> getAllByOwner(User owner) {
        return cardBoxRepository.findAllByOwner(owner);
    }

    @Override
    public Box getByIdAndOwner(long id, User owner) {
        return cardBoxRepository.findByIdAndOwner(id, owner);
    }

    @Override
    public Box save(Box box) {
        return cardBoxRepository.save(box);
    }

    @Override
    public void delete(long cardBoxId) {
        cardBoxRepository.deleteById(cardBoxId);
    }
}
