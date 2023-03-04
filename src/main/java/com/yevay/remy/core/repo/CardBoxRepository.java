package com.yevay.remy.core.repo;

import com.yevay.remy.model.domain.Box;
import com.yevay.remy.model.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardBoxRepository extends CrudRepository<Box, Long> {

    Page<Box> findByOwner(Pageable pageable, User owner);

    List<Box> findAllByOwner(User owner);

    Box findByIdAndOwner(long id, User owner);

}
