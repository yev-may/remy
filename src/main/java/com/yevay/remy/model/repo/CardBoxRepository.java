package com.yevay.remy.model.repo;

import com.yevay.remy.model.domain.CardBox;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardBoxRepository extends CrudRepository<CardBox, Long> {

}
