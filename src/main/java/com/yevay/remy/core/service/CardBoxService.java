package com.yevay.remy.core.service;

import com.yevay.remy.model.domain.CardBox;
import com.yevay.remy.model.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CardBoxService {

    Page<CardBox> getByOwner(Pageable pageable, User owner);

    List<CardBox> getAllByOwner(User owner);

    CardBox getByIdAndOwner(long id, User owner);

    CardBox save(CardBox cardBox);

    void delete(long cardBoxId);
}
