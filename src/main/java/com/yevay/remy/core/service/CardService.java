package com.yevay.remy.core.service;

import com.yevay.remy.model.domain.Card;
import com.yevay.remy.model.domain.Box;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CardService {

    List<Card> getByBoxIdAndLevel(long boxId, int level);

    Page<Card> getByBox(Box box, Pageable pageable);

    void save(Card card);

}
