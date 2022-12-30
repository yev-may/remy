package com.yevay.remy.core.service;

import com.yevay.remy.model.domain.Card;
import com.yevay.remy.model.domain.CardBox;

import java.util.List;

public interface CardService {
    List<Card> getByBox(CardBox box);
    void save(Card card);
}
