package com.yevay.remy.model.repo;

import com.yevay.remy.model.domain.CardBox;
import com.yevay.remy.model.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardBoxRepository extends CrudRepository<CardBox, Long> {
    List<CardBox> findAllByOwner(User owner);
    CardBox findByIdAndOwner(long id, User owner);
}
