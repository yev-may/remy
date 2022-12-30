package com.yevay.remy.model.repo;

import com.yevay.remy.model.domain.Card;
import com.yevay.remy.model.domain.CardBox;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {
    List<Card> findByBox(CardBox box);
}
