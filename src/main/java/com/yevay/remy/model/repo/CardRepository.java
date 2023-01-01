package com.yevay.remy.model.repo;

import com.yevay.remy.model.domain.Card;
import com.yevay.remy.model.domain.CardBox;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Page<Card> findByBox(CardBox box, Pageable pageable);
}
