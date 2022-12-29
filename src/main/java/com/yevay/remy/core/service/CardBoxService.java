package com.yevay.remy.core.service;

import com.yevay.remy.model.domain.CardBox;
import com.yevay.remy.model.domain.User;

import java.util.List;

public interface CardBoxService {
    List<CardBox> getAllForUser(User user);
    void save(CardBox cardBox);
}
