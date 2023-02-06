package com.yevay.remy.model.repo;

import com.yevay.remy.model.domain.Card;
import com.yevay.remy.model.domain.CardBox;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findByBoxIdAndRepeatLevel(long boxId, int level);

    Page<Card> findByBox(CardBox box, Pageable pageable);

}
